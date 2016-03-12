/**
 * Copyright (c) 2010-2012, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 10/07/2012
 * 
 * @version 1.0
 * 
 *          Classe responsável por gerenciar as pesquisas de dados
 */
package br.com.bse.system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import br.com.bse.business.Compromisso;
import br.com.bse.db.dao.CompromissoDAO;
import br.com.bse.pattern.Util;

public class SearchCompromissoManager {
	private List<Integer> ids;
	private List<String> titulos;
	private List<Byte> tipos;
	private List<List<Date>> datas;
	private List<List<String>> horas;
	private List<List<Integer>> contatos;
	private List<List<Integer>> grupos;

	private static SearchCompromissoManager baseSearch = new SearchCompromissoManager();

	@SuppressWarnings({ "unchecked" })
	private SearchCompromissoManager() {
		ids = new ArrayList<Integer>();
		titulos = new ArrayList<String>();
		tipos = new ArrayList<Byte>();
		datas = new ArrayList<List<Date>>();
		horas = new ArrayList<List<String>>();
		contatos = new ArrayList<List<Integer>>();
		grupos = new ArrayList<List<Integer>>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(GeralProperty.ROOT + "cmp.index"));
			String line = null;
			String[] dados = null, subDados = null;
			List subList = null;
			while((line = br.readLine()) != null) {
				dados = line.split("[|]");

				if(dados.length == 7) {
					try {
						ids.add(Integer.parseInt(dados[0]));
						titulos.add(dados[1]);
						tipos.add(Byte.parseByte(dados[2]));
						subDados = dados[3].split(",");
						// Carrega as datas
						if(subDados.length > 0) {
							subList = new ArrayList<Date>();
							Calendar c = Calendar.getInstance();
							String[] campos = null;
							for(int i = 0; i < subDados.length; i++) {
								try {
									campos = subDados[i].split("/");
									c.set(Integer.parseInt(campos[2]), Integer.parseInt(campos[1]),
											Integer.parseInt(campos[0]));
									subList.add(c.getTime());
								} catch(Exception e) {
									e.printStackTrace();
								}
							}
							datas.add(subList);
						}
						// Carrega os horários
						subDados = dados[4].split(",");
						if(subDados.length > 0) {
							subList = new ArrayList<String>();
							for(int i = 0; i < subDados.length; i++)
								subList.add(subDados[i]);
							horas.add(subList);
						}
						// Carrega os IDs dos contatos
						subDados = dados[5].split(",");
						if(subDados.length > 0) {
							subList = new ArrayList<Integer>();
							for(int i = 0; i < subDados.length; i++) {
								try {
									subList.add(Integer.parseInt(subDados[i]));
								} catch(NumberFormatException e) {
								}
							}
							contatos.add(subList);
						}
						// Carrega os IDs dos grupos
						subDados = dados[6].split(",");
						if(subDados.length > 0) {
							subList = new ArrayList<Integer>();
							for(int i = 0; i < subDados.length; i++) {
								try {
									subList.add(Integer.parseInt(subDados[i]));
								} catch(NumberFormatException e) {
								}
							}
							grupos.add(subList);
						}
					} catch(Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Insere um registro no arquivo de pesquisa.
	 * 
	 * @param c
	 *            Compromisso que foi inserido
	 * @return true se foi inserido com sucesso
	 */
	@SuppressWarnings("unchecked")
	public static boolean insertIndex(Compromisso c) {
		try {
			RandomAccessFile raf = new RandomAccessFile(GeralProperty.ROOT + "cmp.index", "rws");

			String line = "\n";
			// 110|Teste 1|1|1/9/2012,10/05/2012|11:02,20:00|1|1
			line += c.getId() + "|";
			line += c.getTitulo() + "|";
			line += c.getTipo() + "|";

			// Prepara as datas
			Iterator i = c.getDatas().iterator();
			if(i.hasNext()) {
				line += Util.formataDataBr((Date) i.next());
				while(i.hasNext())
					line += "," + Util.formataDataBr((Date) i.next());
			}
			line += "|";

			// Prepara os horários
			i = c.getHorarios().iterator();
			if(i.hasNext()) {
				line += Util.formataHora((Date) i.next());
				while(i.hasNext())
					line += "," + Util.formataHora((Date) i.next());
			}
			line += "|";

			// Prepara os IDs dos contatos
			i = c.getAvisoEmail().getEmailContatos().keySet().iterator();
			if(i.hasNext()) {
				line += i.next();
				while(i.hasNext())
					line += "," + i.next();

				i = c.getAvisoSms().getCelularContatos().keySet().iterator();
				while(i.hasNext())
					line += "," + i.next();
			} else {
				i = c.getAvisoSms().getCelularContatos().keySet().iterator();
				if(i.hasNext()) {
					line += i.next();
					while(i.hasNext())
						line += "," + i.next();
				}
			}
			line += "|";

			// Prepara os IDs dos grupos
			i = c.getAvisoEmail().getGrupos().iterator();
			if(i.hasNext()) {
				line += i.next();
				while(i.hasNext())
					line += "," + i.next();

				i = c.getAvisoSms().getCelularContatos().keySet().iterator();
				while(i.hasNext())
					line += "," + i.next();
			} else {
				i = c.getAvisoSms().getGrupos().iterator();
				if(i.hasNext()) {
					line += i.next();
					while(i.hasNext())
						line += "," + i.next();
				}
			}

			raf.seek(raf.length());
			raf.write(line.getBytes());
			raf.close();
			SearchCompromissoManager.baseSearch = new SearchCompromissoManager();
			return true;
		} catch(IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Remove um registro no arquivo de pesquisa.
	 * 
	 * @param c
	 *            Compromisso que foi inserido
	 * @return true se foi deletado com sucesso
	 */
	public static boolean deleteIndex(Compromisso c) {
		int index = baseSearch.ids.indexOf(c.getId());

		if(index < 0)
			return true;
		try {
			baseSearch.ids.remove(index);
			baseSearch.titulos.remove(index);
			baseSearch.tipos.remove(index);
			baseSearch.datas.remove(index);
			baseSearch.horas.remove(index);
			baseSearch.contatos.remove(index);
			baseSearch.grupos.remove(index);

			baseSearch.saveBaseIndex();
		} catch(Exception ex) {
			// baseSearch = new SearchCompromissoManager();
			ex.printStackTrace();
		}
		return true;
	}

	/**
	 * Salva os dados do index dos compromissos no arquivo cmp.index.
	 * 
	 * @return true se foi salvado com sucesso.
	 * @throws Exception
	 *             se ocorrer algum erro é lançado uma Exception
	 */
	private boolean saveBaseIndex() throws Exception {
		try {
			File f = new File(GeralProperty.ROOT + "cmp.index");
			System.out.println(f.renameTo(new File(GeralProperty.ROOT + "cmp.index.bkp")));

			BufferedWriter bw = new BufferedWriter(new FileWriter(GeralProperty.ROOT + "cmp.index"));
			StringBuffer sb = new StringBuffer();
			Iterator<Date> iterator = null;

			int size = baseSearch.ids.size();
			for(int i = 0; i < size; i++) {
				sb.append(baseSearch.ids.get(i) + "|");
				sb.append(baseSearch.titulos.get(i) + "|");
				sb.append(baseSearch.tipos.get(i) + "|");

				// sb.append(baseSearch.datas.get(i).toString().replace("[",
				// "").replace("]", "").replace(" ", "") + "|");
				iterator = baseSearch.datas.get(i).iterator();
				while(iterator.hasNext()) {
					sb.append(Util.formataDataBr(iterator.next()));
					if(iterator.hasNext()) {
						sb.append(",");
					}
				}
				sb.append("|");
				sb.append(baseSearch.horas.get(i).toString().replace("[", "").replace("]", "").replace(" ", "") + "|");
				sb.append(baseSearch.contatos.get(i).toString().replace("[", "").replace("]", "").replace(" ", "")
						+ "|");
				sb.append(baseSearch.grupos.get(i).toString().replace("[", "").replace("]", "").replace(" ", ""));
				sb.append("\n");
			}
			bw.write(sb.toString());
			bw.flush();
			bw.close();
			return true;
		} catch(Exception ex) {
			File f = new File(GeralProperty.ROOT + "cmp.index");
			f.delete();
			f = new File(GeralProperty.ROOT + "cmp.index.bkp");
			f.renameTo(new File(GeralProperty.ROOT + "cmp.index"));

			throw ex;
		}
	}

	/**
	 * Pesquisa um Compromisso pelo ID.
	 * 
	 * @param id
	 *            ID do compromisso
	 * @return Compromisso encontrado
	 */
	public static Compromisso searchById(int id) {
		int index = baseSearch.ids.indexOf(id);
		if(index >= 0) {
			CompromissoDAO cDao = new CompromissoDAO();
			Date data = null;
			List<Date> list = baseSearch.datas.get(index);
			if(list.size() > 0) {
				Collections.sort(list);
				data = list.get(list.size() - 1);
				return cDao.get(data, id);
			}
		}
		return null;
	}

	/**
	 * Pesquisa um Compromisso pelo titulo.
	 * 
	 * @param titulo
	 *            titulo do compromisso
	 * @return Compromisso encontrado
	 */
	public static Compromisso searchByTitulo(String titulo) {
		int index = baseSearch.titulos.indexOf(titulo);

		if(index >= 0) {
			CompromissoDAO cDao = new CompromissoDAO();
			Date data = null;
			int id = baseSearch.ids.get(index);
			List<Date> list = baseSearch.datas.get(index);
			if(list.size() > 0) {
				Collections.sort(list);
				data = list.get(list.size() - 1);
				return cDao.get(data, id);
			}
		}
		return null;
	}

	/**
	 * Pesquisa os compromissos pelo ID do tipo do compromisso.
	 * 
	 * @param idTipo
	 *            ID do tipo do compromisso
	 * @return List contendo os Compromissos encontrados
	 */
	public static List<Compromisso> searchByTipo(byte idTipo) {
		List<Compromisso> listFind = new ArrayList<Compromisso>();
		Compromisso c = null;
		int size = baseSearch.tipos.size();
		int id = 0;

		for(int i = 0; i < size; i++) {
			if(baseSearch.tipos.get(i) == idTipo) {
				id = baseSearch.ids.get(i);
				if((c = searchById(id)) != null) {
					listFind.add(c);
				}
			}
		}

		if(listFind.size() > 0)
			return listFind;
		else
			return null;
	}

	/**
	 * Pesquisa os compromissos pela data.
	 * 
	 * @param data
	 *            data do compromisso
	 * @return List contendo os Compromissos encontrados
	 */
	public static List<Compromisso> searchByData(Date data) {
		CompromissoDAO cDao = new CompromissoDAO();
		return cDao.getByData(data);
	}

	/**
	 * Pesquisa os compromissos pela hora.
	 * 
	 * @param hora
	 *            hora do compromisso
	 * @return List contendo os Compromissos encontrados
	 */
	public static List<Compromisso> searchByHora(String hora) {
		List<Compromisso> listFind = new ArrayList<Compromisso>();
		Compromisso c = null;
		int size = baseSearch.horas.size();
		int id = 0;
		List<String> list = null;

		for(int i = 0; i < size; i++) {
			if((list = baseSearch.horas.get(i)) != null) {
				if(!list.contains(hora))
					continue;

				id = baseSearch.ids.get(i);
				if((c = searchById(id)) != null) {
					listFind.add(c);
				}

			}
		}

		if(listFind.size() > 0)
			return listFind;
		else
			return null;
	}

	/**
	 * Pesquisa os compromissos pelo ID do contato que participa do compromisso.
	 * 
	 * @param idContato
	 *            ID do contato do compromisso
	 * @return List contendo os Compromissos encontrados
	 */
	public static List<Compromisso> searchByContato(int idContato) {
		List<Compromisso> listFind = new ArrayList<Compromisso>();
		Compromisso c = null;
		int size = baseSearch.contatos.size();
		int id = 0;
		List<Integer> list = null;

		for(int i = 0; i < size; i++) {
			if((list = baseSearch.contatos.get(i)) != null) {
				if(!list.contains(idContato))
					continue;

				id = baseSearch.ids.get(i);
				if((c = searchById(id)) != null) {
					listFind.add(c);
				}

			}
		}

		if(listFind.size() > 0)
			return listFind;
		else
			return null;
	}

	/**
	 * Pesquisa os compromissos pelo ID do grupo que participa do compromisso.
	 * 
	 * @param idGrupo
	 *            ID do grupo do compromisso
	 * @return List contendo os Compromissos encontrados
	 */
	public static List<Compromisso> searchByGrupo(int idGrupo) {
		List<Compromisso> listFind = new ArrayList<Compromisso>();
		Compromisso c = null;
		int size = baseSearch.grupos.size();
		int id = 0;
		List<Integer> list = null;

		for(int i = 0; i < size; i++) {
			if((list = baseSearch.grupos.get(i)) != null) {
				if(!list.contains(idGrupo))
					continue;

				id = baseSearch.ids.get(i);
				if((c = searchById(id)) != null) {
					listFind.add(c);
				}

			}
		}

		if(listFind.size() > 0)
			return listFind;
		else
			return null;
	}

}
