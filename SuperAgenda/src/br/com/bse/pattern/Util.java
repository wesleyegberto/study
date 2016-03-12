/**
 * Copyright (c) 2010-2012, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 30/06/2012
 * 
 * @version 1.0
 * 
 *          Classe responsável por oferecer métodos utilitários.
 */
package br.com.bse.pattern;

import java.util.Calendar;
import java.util.Date;

public class Util {
	/**
	 * 
	 */
	private Util() {
	}

	/**
	 * Retorna a data com formato brasileiro. dd/mm/yyyy
	 * 
	 * @param date
	 *            data que será formatada
	 * @return String contendo a data formatada
	 */
	public static String formataDataBr(Date date) {
		if(date == null)
			return "";
		else {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			return String.format("%02d/%02d/%02d", c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH),
					c.get(Calendar.YEAR));
		}
	}

	/**
	 * Retorna a data com formato americano. mm/dd/yyyy
	 * 
	 * @param date
	 *            data que será formatada
	 * @return String contendo a data formatada
	 */
	public static String formataDataUs(Date date) {
		if(date == null)
			return "";
		else {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			return String.format("%02d/%02d/%02d", c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH),
					c.get(Calendar.YEAR));
		}
	}

	/**
	 * Retorna a hora no format hh:mm:ss.
	 * 
	 * @param time
	 *            Date contendo a hora
	 * @return String contendo a hora formatada
	 */
	public static String formataHoraCompleta(Date time) {
		if(time == null)
			return "";
		else {
			Calendar c = Calendar.getInstance();
			c.setTime(time);
			return String.format("%02d:%02d:%02d", c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
					c.get(Calendar.SECOND));
		}
	}

	/**
	 * Retorna a hora no format hh:mm.
	 * 
	 * @param time
	 *            Date contendo a hora
	 * @return String contendo a hora formatada
	 */
	public static String formataHora(Date time) {
		if(time == null)
			return "";
		else {
			Calendar c = Calendar.getInstance();
			c.setTime(time);
			return String.format("%02d:%02d", c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));
		}
	}

	/**
	 * Retorna um Date com o dia, mês e ano recebido
	 * 
	 * @param day
	 *            dia a ser setado
	 * @param month
	 *            mês a ser setado
	 * @param year
	 *            ano a ser setado
	 * @return Date contendo a data
	 */
	public static Date getNewDate(int year, int month, int day) {
		Calendar c = Calendar.getInstance();
		c.set(year, month, day);
		return c.getTime();
	}

	/**
	 * Retorna um Date com o dia, mês e ano recebido
	 * 
	 * @param date
	 *            String contendo a data no formato dd/mm/yyyy
	 * @return Date contendo a data
	 */
	public static Date getNewDateFromDataBr(String date) {
		return null;
	}

	/**
	 * Retorna um Date contendo a hora recebida.
	 * 
	 * @param hora
	 *            String contendo a hora no formato hh:mm ou hh:mm:ss
	 * @return Date contendo a hora, senão null caso a hora seja inválida
	 */
	public static Date getNewTimeFrom(String hora) {
		int hour = -1;
		int minute = -1;
		int second = -1;

		if(hora.matches("\\d{2}:\\d{2}") == true) {
			String[] times = hora.split(":");
			hour = Integer.parseInt(times[0]);
			minute = Integer.parseInt(times[1]);
			second = 0;
		} else if(hora.matches("\\d{2}:\\d{2}:\\d{2}") == true) {
			String[] times = hora.split(":");
			hour = Integer.parseInt(times[0]);
			minute = Integer.parseInt(times[1]);
			second = Integer.parseInt(times[2]);
		} else {
			return null;
		}

		try {
			if(hour < 0 || hour > 23 || minute < 0 || minute > 59 || second < 0 || second > 59)
				return null;

			Calendar c = Calendar.getInstance();

			c.set(Calendar.HOUR_OF_DAY, hour);
			c.set(Calendar.MINUTE, minute);
			c.set(Calendar.SECOND, second);

			return c.getTime();
		} catch(NumberFormatException ex) {
			return null;
		}
	}

}
