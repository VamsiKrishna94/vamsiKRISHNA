/*
 * Copyright 2015 All Rights Reserved.
 */

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Logger;

import runner.Generator;
import runner.WeatherModel;
import runner.Alerts;
import model.Item;
import model.Alert;
import parser.ItemParser;
import reader.SingleItemReader;

/**
 * Main class is the class to run this program
 * 
 * @author <a href="mailto:jingyuzhang2008@gmail.com">Jingyu ZHANG</a>
 * @version  1.0
 * @since May 2016
 **/
public class Main {
	private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

	/**
	 * 
	 * @param args
	 *            need two Absolute Paths.
	 * @throws Exception
	 *             throw all type of exceptions
	 */
	public static void main(String[] args) {

		if (args == null | args.length < 5) {
			LOGGER.severe("parameters are not enough, Main.class required 5 parameters. Two Absolute Paths.\n"
					+ " One is the file cotains row data that will put in Model, another is the Alerts file.\n"
					+ " a start date, a duration, and a outputPath.\n"
					+ "e.g.  java -cp Interview.jar Main items.txt alerts.json 2016-06-20 30 output.txt"
					);
			System.exit(1);
		}
		Boolean result;
		try {
			// load dictionary from 
			SingleItemReader<Item> singleLogReader = new SingleItemReader<Item>(
					new ItemParser(), new File(args[0]));
			WeatherModel<Item> weatherModel = new WeatherModel<Item>(singleLogReader);
			// load Alert
			Alerts alert = new Alerts(args[1]);
			// prepare generator
			Generator generator = new Generator(weatherModel, alert);
			String startDate = args[2];
			int duration = Integer.parseInt(args[3]);
			String outputPath = args[4];
			result = generator.generateData(startDate, duration,outputPath);
			System.out.println("--------------------------------------------");
			if (result != null)
				System.out.println("done!");
			else
				System.out.println("Sorry, Something wrong.");
		} catch (IOException e) {
			System.out.println("The file format is not valiad.");
			e.printStackTrace();
			System.exit(1);
		} catch (ParseException e) {
			System.out.println("The file can not be parsed. ");
			System.exit(-1);
		} catch (Exception e) {
			System.out.println("Exception found :"  + e.toString());
			
			System.exit(-1);
		}

		System.out.println("--------------------------------------------");
	}
}
