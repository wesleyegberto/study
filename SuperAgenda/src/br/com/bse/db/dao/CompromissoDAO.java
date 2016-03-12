/**
 * Copyright (c) 2010-2012, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito e William Carvalho Noronha 27/05/2012
 * 
 * @version 1.0
 * 
 *          Classe DAO responsável pela persistência dos tipos Compromisso
 */
package br.com.bse.db.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import br.com.bse.business.AvisoEmail;
import br.com.bse.business.AvisoPc;
import br.com.bse.business.AvisoSms;
import br.com.bse.business.Compromisso;
import br.com.bse.business.Musica;
import br.com.bse.pattern.FileManager;
import br.com.bse.pattern.Util;
import br.com.bse.pattern.XMLCreator;
import br.com.bse.pattern.XMLException;
import br.com.bse.pattern.XMLReader;
import br.com.bse.system.GeralProperty;
import br.com.bse.system.SearchCompromissoManager;

public class CompromissoDAO implements DAO<Compromisso> {
	private String pathRelative = GeralProperty.ROOT + "compromisso" + File.separator;
	private XMLCreator xmlCreator;

	public CompromissoDAO() {
		xmlCreator = new XMLCreator("COMPROMISSO");
	}

	public XMLCreator getXmlCreator() {
		return xmlCreator;
	}

	/**
	 * A lista de data DEVE estar em orderm decrescente
	 * 
	 * @param c
	 *            objeto Compromisso que será persistido
	 * 
	 * @return true se foi persistido com sucesso
	 * @see br.com.bse.db.dao.DAO#insert(java.lang.Object)
	 */
	public boolean insert(Compromisso c) {
		xmlCreator.clearBody();

		Iterator<?> list;
		String h = null;
		Date d = null;

		try {
			if(c.getId() > 0)
				xmlCreator.add("ID", c.getId());

			if(c.getTitulo() != null)
				xmlCreator.add("TITULO", c.getTitulo());

			if(c.getTipo() > 0)
				xmlCreator.add("ID_TIPO", c.getTipo());

			if(c.getDataCriacao() != null)
				xmlCreator.add("DATA_CRIACAO", Util.formataDataBr(c.getDataCriacao()));

			if(c.getDescricao() != null)
				xmlCreator.add("DESCRICAO", c.getDescricao());

			list = c.getHorarios().iterator();
			while(list.hasNext()) {
				// Salva o XML principal
				h = Util.formataHora((Date) list.next());
				xmlCreator.add("HORA", h, false);
			}

			if(c.getAvisoPc() != null) {
				xmlCreator.addNode("AVISO_PC", true);
				xmlCreator.addNodeOn("COMPROMISSO.AVISO_PC", "MUSICA", true);
				xmlCreator.addElementOn("COMPROMISSO.AVISO_PC.MUSICA", "CAMINHO", c.getAvisoPc().getMusica()
						.getCaminho().getAbsolutePath(), true);
				xmlCreator.addElementOn("COMPROMISSO.AVISO_PC.MUSICA", "VOLUME", c.getAvisoPc().getMusica().getVolume()
						+ "", true);
				xmlCreator.addElementOn("COMPROMISSO.AVISO_PC.MUSICA", "INICIO",
						c.getAvisoPc().getMusica().getInicio(), true);
				xmlCreator
						.addElementOn("COMPROMISSO.AVISO_PC.MUSICA", "FIM", c.getAvisoPc().getMusica().getFim(), true);
			}

			if(c.getAvisoEmail() != null) {
				xmlCreator.addNode("AVISO_EMAIL", true);
				list = c.getAvisoEmail().getDestinatarios().iterator();
				while(list.hasNext()) {
					xmlCreator.addElementOn("COMPROMISSO.AVISO_EMAIL", "DESTINATARIO", list.next().toString(), false);
				}

				list = c.getAvisoEmail().getGrupos().iterator();
				if(list.hasNext()) {
					xmlCreator.addNodeOn("COMPROMISSO.AVISO_EMAIL", "GRUPOS", true);
					while(list.hasNext()) {
						xmlCreator.addElementOn("COMPROMISSO.AVISO_EMAIL.GRUPOS", "ID", list.next().toString(), false);
					}
				}

				Iterator<Entry<Integer, Integer>> listE = c.getAvisoEmail().getEmailContatos().entrySet().iterator();
				Entry<Integer, Integer> entry;

				if(listE.hasNext()) {
					xmlCreator.addNodeOn("COMPROMISSO.AVISO_EMAIL", "EMAILS", true);
					while(listE.hasNext()) {
						entry = listE.next();
						xmlCreator.addElementOn("COMPROMISSO.AVISO_EMAIL.EMAILS", "ID",
								entry.getKey() + "|" + entry.getValue(), false);
					}
				}
			}

			if(c.getAvisoSms() != null) {
				xmlCreator.addNode("AVISO_SMS", true);
				list = c.getAvisoSms().getNumeros().iterator();
				while(list.hasNext()) {
					xmlCreator.addElementOn("COMPROMISSO.AVISO_SMS", "NUMERO", list.next().toString(), false);
				}

				Iterator<Entry<Integer, Integer>> listE = c.getAvisoEmail().getEmailContatos().entrySet().iterator();
				Entry<Integer, Integer> entry;

				list = c.getAvisoSms().getGrupos().iterator();
				if(list.hasNext()) {
					xmlCreator.addNodeOn("COMPROMISSO.AVISO_SMS", "GRUPOS", true);
					while(list.hasNext()) {
						xmlCreator.addElementOn("COMPROMISSO.AVISO_SMS.GRUPOS", "ID", list.next().toString(), false);
					}
				}

				if(listE.hasNext()) {
					xmlCreator.addNodeOn("COMPROMISSO.AVISO_SMS", "CELULARES", true);
					while(listE.hasNext()) {
						entry = listE.next();
						xmlCreator.addElementOn("COMPROMISSO.AVISO_SMS.CELULARES", "ID",
								entry.getKey() + "|" + entry.getValue(), false);
					}
				}
			}

			// Salva as datas do Compromisso
			Calendar calendar = Calendar.getInstance();
			Iterator<Date> iterator = c.getDatas().iterator();
			String path = null;

			Collections.sort(c.getDatas());

			d = c.getDatas().get(c.getDatas().size() - 1);
			calendar.setTime(d);
			// Salva o caminho do último arquivo do compromisso que conterá os
			// dados
			String lastPath = calendar.get(Calendar.YEAR) + File.separator + calendar.get(Calendar.MONTH)
					+ File.separator + calendar.get(Calendar.DAY_OF_MONTH) + File.separator + c.getId() + ".cmp";

			while(iterator.hasNext()) {
				d = iterator.next();
				calendar.setTime(d);
				xmlCreator.add("DATA", Util.formataDataBr(d), false);
				// Cria o path a partir do /compromisso até a pasta do arquivo
				path = pathRelative + calendar.get(Calendar.YEAR) + File.separator + calendar.get(Calendar.MONTH)
						+ File.separator + calendar.get(Calendar.DAY_OF_MONTH) + File.separator + c.getId() + ".cmp";

				if(iterator.hasNext()) {
					FileManager.saveFile(path, lastPath);
				} else {
					FileManager.saveFile(path, xmlCreator.getXml());
					break;
				}
			}
			SearchCompromissoManager.insertIndex(c);
			return true;
		} catch(XMLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * A lista de data DEVE estar em orderm decrescente
	 * 
	 * @param c
	 *            objeto Compromisso que será atualizado
	 * 
	 * @return true se foi atualizado com sucesso
	 * @see br.com.bse.db.dao.DAO#update(java.lang.Object)
	 */
	public boolean update(Compromisso c) {
		return insert(c);
	}

	/**
	 * Delete os dados persistido do objeto Compromisso.
	 * 
	 * @param c
	 *            objeto do tipo Compromisso a ser deletado, deve-se utilizar um
	 *            campo como chave de pesquisa
	 * @return true se foi deletado com sucesso
	 * @see br.com.bse.db.dao.DAO#delete(java.lang.Object)
	 */
	public boolean delete(Compromisso c) {
		if(c == null || c.getDatas() == null)
			return false;
		else if(c.getDatas().size() == 0 && c.getId() > 0)
			c = SearchCompromissoManager.searchById(c.getId());
		else if(c.getId() == 0)
			return true;

		Calendar calendar = Calendar.getInstance();
		Iterator<Date> iterator = c.getDatas().iterator();
		String path = null;

		while(iterator.hasNext()) {
			calendar.setTime(iterator.next());
			path = pathRelative + calendar.get(Calendar.YEAR) + File.separator + calendar.get(Calendar.MONTH)
					+ File.separator + calendar.get(Calendar.DAY_OF_MONTH) + File.separator + c.getId() + ".cmp";
			System.out.print(path + " : ");
			System.out.println(FileManager.delete(path));
		}

		return SearchCompromissoManager.deleteIndex(c);
	}

	/**
	 * Busca os dados do objeto Compromisso no diretório recebido.
	 * 
	 * @param pathFile
	 *            objeto do tipo Compromisso a ser carregado com os dados do
	 *            registro, deve-se utilizar um campo como chave de pesquisa
	 * @return Compromisso contendo o objeto encontrado
	 * @see br.com.bse.db.dao.DAO#get(java.lang.Object)
	 */
	@SuppressWarnings("resource")
	public Compromisso get(String pathFile) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(pathFile));
			String line = br.readLine();
			Compromisso c;

			if(FileManager.exist(this.pathRelative + line)) {
				return get(this.pathRelative + line);
			}

			XMLReader xmlReader = new XMLReader(pathFile);
			c = new Compromisso();
			List<String> list = null;
			Iterator<String> iterator = null;
			String value = null, values[] = null;

			c.setId(xmlReader.getIntValue("COMPROMISSO", "ID"));
			c.setTitulo(xmlReader.getStringValue("COMPROMISSO", "TITULO"));
			c.setTipo(xmlReader.getByteValue("COMPROMISSO", "ID_TIPO"));
			value = xmlReader.getStringValue("COMPROMISSO", "DATA_CRIACAO");
			if(value != null)
				values = value.split("/");

			if(values != null && values.length == 3) {
				try {
					int d = Integer.parseInt(values[0]);
					int m = Integer.parseInt(values[1]);
					int y = Integer.parseInt(values[2]);
					Calendar cal = Calendar.getInstance();
					cal.set(y, m, d);
					c.setDataCriacao(cal.getTime());
				} catch(Exception ex) {
				}
			}

			// Carrega as datas do compromisso
			list = xmlReader.getValues("COMPROMISSO", "DATA");
			if(list != null) {
				iterator = list.iterator();
				while(iterator.hasNext()) {
					try {
						values = iterator.next().split("/");
						int d = Integer.parseInt(values[0]);
						int m = Integer.parseInt(values[1]);
						int y = Integer.parseInt(values[2]);
						Calendar cal = Calendar.getInstance();
						cal.set(y, m, d);
						c.addData(cal.getTime());
					} catch(Exception ex) {
						ex.printStackTrace();
					}
				}
			}

			// Carrega os horários cadastrado
			list = xmlReader.getValues("COMPROMISSO", "HORA");
			if(list != null) {
				iterator = list.iterator();
				while(iterator.hasNext()) {
					try {
						c.addHorario(Util.getNewTimeFrom(iterator.next()));
					} catch(Exception ex) {
					}
				}
			}

			// Trata o AvisoPC caso ele exista
			if(xmlReader.exists("COMPROMISSO.AVISO_PC.MUSICA")) {
				AvisoPc avisoPc = new AvisoPc();
				Musica m = new Musica(xmlReader.getStringValue("COMPROMISSO.AVISO_PC.MUSICA", "CAMINHO"),
						xmlReader.getByteValue("COMPROMISSO.AVISO_PC.MUSICA", "VOLUME"), xmlReader.getStringValue(
								"COMPROMISSO.AVISO_PC.MUSICA", "INICIO"), xmlReader.getStringValue(
								"COMPROMISSO.AVISO_PC.MUSICA", "FIM"));
				avisoPc.setMusica(m);
				c.setAvisoPc(avisoPc);
			}

			// Trata o AvisoEmail caso ele exita
			if(xmlReader.exists("COMPROMISSO.AVISO_EMAIL")) {
				AvisoEmail avisoEmail = new AvisoEmail();
				list = xmlReader.getValues("COMPROMISSO.AVISO_EMAIL", "DESTINATARIO");
				if(list != null) {
					iterator = list.iterator();
					while(iterator.hasNext()) {
						try {
							avisoEmail.addContato(iterator.next());
						} catch(Exception ex) {
						}
					}
				}
				list = xmlReader.getValues("COMPROMISSO.AVISO_EMAIL.GRUPOS", "ID");
				if(list != null) {
					iterator = list.iterator();
					while(iterator.hasNext()) {
						try {
							avisoEmail.addGrupo(Integer.parseInt(iterator.next()));
						} catch(Exception ex) {
							ex.printStackTrace();
						}
					}
				}
				list = xmlReader.getValues("COMPROMISSO.AVISO_EMAIL.EMAILS", "ID");
				if(list != null) {
					iterator = list.iterator();
					while(iterator.hasNext()) {
						try {
							values = iterator.next().split("|");
							avisoEmail.addContato(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
						} catch(Exception ex) {
						}
					}
				}
				c.setAvisoEmail(avisoEmail);
			}

			// Trata o AvisoSms caso ele exista
			if(xmlReader.exists("COMPROMISSO.AVISO_SMS")) {
				AvisoSms avisoSms = new AvisoSms();
				list = xmlReader.getValues("COMPROMISSO.AVISO_SMS", "NUMERO");
				if(list != null) {
					iterator = list.iterator();
					while(iterator.hasNext()) {
						try {
							avisoSms.addContato(iterator.next());
						} catch(Exception ex) {
						}
					}
				}
				list = xmlReader.getValues("COMPROMISSO.AVISO_SMS.GRUPOS", "ID");
				if(list != null) {
					iterator = list.iterator();
					while(iterator.hasNext()) {
						try {
							avisoSms.addGrupo(Integer.parseInt(iterator.next()));
						} catch(Exception ex) {
							ex.printStackTrace();
						}
					}
				}
				list = xmlReader.getValues("COMPROMISSO.AVISO_SMS.CELULARES", "ID");
				if(list != null) {
					iterator = list.iterator();
					while(iterator.hasNext()) {
						try {
							values = iterator.next().split("|");
							avisoSms.addContato(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
						} catch(Exception ex) {
						}
					}
				}
				c.setAvisoSms(avisoSms);
			}
			br.close();
			return c;
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Busca todos os dados dos objetos do tipo Compromisso persistido.
	 * 
	 * @return uma List de objetos do tipo Compromisso com todos os registros
	 *         encontrados
	 * @see br.com.bse.db.dao.DAO#getAll()
	 */
	public List<Compromisso> getAll() {
		return null;
	}

	/**
	 * Retorna os objetos Compromisso a partir da pesquisa.
	 * 
	 * @param fieldName
	 *            nome do campo que será usado como filtro
	 * @param value
	 *            valor para o campo
	 * 
	 * @return List com os objetos Compromisso encontrado
	 * @see br.com.bse.db.dao.DAO#getByField(java.lang.String, java.lang.String)
	 */
	public List<Compromisso> getByField(String fieldName, String value) {
		return null;
	}

	/**
	 * Retorna os objetos Compromisso a partir da data.
	 * 
	 * @param data
	 *            Data dos compromissos
	 * 
	 * @return List com os objetos Compromisso encontrado ou null se não foi
	 *         encontrado nenhum
	 */
	public List<Compromisso> getByData(Date data) {
		File f, files[];
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		String path = pathRelative + calendar.get(Calendar.YEAR) + File.separator + calendar.get(Calendar.MONTH)
				+ File.separator + calendar.get(Calendar.DAY_OF_MONTH);

		f = new File(path);

		if(!f.isDirectory())
			return null;

		List<Compromisso> list = new ArrayList<Compromisso>();
		Compromisso c = null;
		files = f.listFiles();
		int length = files.length;

		for(int i = 0; i < length; i++) {
			c = get(files[i].getAbsolutePath());
			if(c != null)
				list.add(c);
		}

		if(list.size() > 0)
			return list;
		else
			return null;
	}

	/**
	 * Retorna o Compromisso a partir de sua data e ID.
	 * 
	 * @param data
	 *            data do compromisso
	 * @param id
	 *            ID do compromisso
	 * @return Compromisso encontrador senão null
	 */
	public Compromisso get(Date data, int id) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		String path = pathRelative + calendar.get(Calendar.YEAR) + File.separator + calendar.get(Calendar.MONTH)
				+ File.separator + calendar.get(Calendar.DAY_OF_MONTH) + File.separator + id + ".cmp";

		return this.get(path);
	}
}
