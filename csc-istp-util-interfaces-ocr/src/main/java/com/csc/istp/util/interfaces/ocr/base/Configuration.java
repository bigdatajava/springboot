package com.csc.istp.util.interfaces.ocr.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Configuration {
	private static final Logger logger = LogManager.getLogger(Configuration.class);
	
	private static char BLOCK_CHAR[] = { '{', '}' };
	public static final String DEFAULT_CONFIG_FILE = "application.properties";
	private static Vector guests = new Vector();
	private static String fileName = null;
	private static Vector parsingNames = new Vector();
	private static Vector parsedNames = new Vector();
	private static Properties p = new Properties();
	private static boolean loaded = false;

	public Configuration() {
	}

	public static  Properties load() {
		logger.info("Will Search [/application.properties] in class path ... ");

		URL url = (new Object() {}).getClass().getResource("/application.properties");
		if (url == null) {
			logger.info("File not found!");
			return p;
		}
		logger.info("find [" + url.toString() + "].");
		logger.info("parsing ... ");
		try {
			load(url);
		} catch (Throwable t) {
			
			logger.info("Error occurs: ");
			t.printStackTrace();
			return p;
		}
		logger.info("done.");
		return p;
	}

	public static  Properties load(URL url) throws IOException {
		if (url == null) {
			throw new FileNotFoundException("Configuration file not found.");
		}
		InputStream in = url.openStream();
		if (in == null) {
			throw new FileNotFoundException("Configuration file not found, [" + url + "]");
		}
		parsingNames.clear();
		parsedNames.clear();
		p.clear();
		p.load(in);
		try {
			in.close();
		} catch (Throwable t) {
			logger.info("Error occurs during close file [" + url + "]");
			t.printStackTrace();
		}
		p.setProperty("current.location", (new File(url.getFile())).getParentFile().getAbsolutePath());
		p.setProperty("current.classpath", (new File(url.getFile())).getAbsolutePath() );
		
		Enumeration e = p.propertyNames();
		Properties sys = System.getProperties();
		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			String value = p.getProperty(name);
			value = parseValue(name, value);
			if (value != null) {
				sys.setProperty(name, value);
			}
		}
		System.setProperties(sys);
		if (!guests.isEmpty()) {
			EnableAutoConfig eacs[] = new EnableAutoConfig[guests.size()];
			int i = 0;
			for (i = 0; i < guests.size(); i++) {
				eacs[i] = (EnableAutoConfig) guests.elementAt(i);
			}

			guests.removeAllElements();
			for (i = 0; i < eacs.length; i++) {
				if (eacs[i] != null) {
					eacs[i].autoConfig();
				}
			}

		}
		loaded = true;
		return p;
	}

	private static String parseValue(String name, String value) throws IOException {
		if (parsedNames.contains(name)) {
			return value;
		}
		if (parsingNames.contains(name)) {
			throw new NestedReferenceException("Nested reference in configration file \"" + fileName + "\"" + parsingNames.toString() + "--> " + name);
		}
		parsingNames.addElement(name);
		String value0 = "";
		StringReader in = new StringReader(value);
		int c = in.read();
		while (c != -1) {
			if (c == BLOCK_CHAR[0] || c == BLOCK_CHAR[1]) {
				int ch = in.read();
				if (ch == c) {
					value0 = value0 + (char) c;
				} else if (c == BLOCK_CHAR[0]) {
					c = ch;
					String name0 = "";
					for (; c != -1; c = in.read()) {
						if (c == BLOCK_CHAR[0]) {
							c = in.read();
							if (c != BLOCK_CHAR[0]) {
								name0 = name0 + (char) c;
							}
							continue;
						}
						if (c == BLOCK_CHAR[1]) {
							c = in.read();
							if (c != BLOCK_CHAR[1]) {
								break;
							}
							name0 = name0 + (char) c;
						} else {
							name0 = name0 + (char) c;
						}
					}

					if (!name0.equals("") && p.containsKey(name0)) {
						value0 = value0 + parseValue(name0, p.getProperty(name0));
					}
					if (c != -1) {
						continue;
					}
				}
			} else {
				value0 = value0 + (char) c;
			}
			c = in.read();
		}
		p.setProperty(name, value0);
		parsingNames.removeElement(name);
		parsedNames.addElement(name);
		return value0;
	}

	public static void register(EnableAutoConfig e) {
		if (!guests.contains(e)) {
			guests.addElement(e);
		}
	}

	protected void finalize() {
		guests.clear();
		guests = null;
	}

	/*
	 * service - Demo调用方法
	 */
	public static String getProperty(String key) {
		load();
		if (p != null) {
			return (String) p.get(key);
		} else {
			return null;
		}
	}

	public static String getProperty(String key, Object defaultValue) {
		String tmpObj = getProperty(key);
		if (tmpObj == null) {
			return defaultValue.toString();
		} else {
			return tmpObj;
		}
	}
}

