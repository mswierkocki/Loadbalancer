package com.mswierk.loadbalancer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Component;

/**
 * Reading config file
 * @author Marcin Świerkocki
 *
 */
@Component
public class Config {
	List<Entry> EntriesList = new ArrayList<Entry>();

	public Config() {
		this.readConfiguration();
	}

	class Entry {
		String name;
		int weight;
	}

	private void readConfiguration() {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			String filename = "config.properties";
			File file = new File(filename);
			input = new FileInputStream(file);
			prop.load(input);

			Enumeration<?> e = prop.propertyNames();
			while (e.hasMoreElements()) {
				Entry ent = new Entry();
				ent.name = (String) e.nextElement();
				ent.weight = Integer.valueOf(prop.getProperty(ent.name));
				EntriesList.add(ent);

			}

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public String[] getDistribution() {
		String[] ret = new String[100];
		int weightSum = 0;
		for (Entry ent : EntriesList) {
			String group = ent.name;
			int weight = ent.weight;
			if (weight <= 0) {
				throw new IllegalArgumentException("Weight cannot be less or equal to 0");
			}
			for (int i = 0; i < weight; i++) {
				ret[i + weightSum] = group;
			}
			weightSum += weight;
		}

		if (weightSum != 100) {
			throw new IllegalArgumentException("Weight does not add up to 100");
		}
		return ret;
	}
}
