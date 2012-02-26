package com.gensoft.domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import com.gensoft.util.Constantes;

public class Marcacao implements Comparable<Marcacao>{
	
	private static DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
	private static DateFormat horaFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
	
	private Date data;
	private String comentario;
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	@Override
	public int compareTo(Marcacao m) {
		return data.compareTo(m.data);
	}
	
	public String formatToWrite(){		
		return dateFormat.format(data)+Constantes.FIELD_TOKEN+comentario+Constantes.FIELD_TOKEN;
	}
	
	public String formatData(){		
		return dateFormat.format(data);
	}
	
	public String formatHora(){		
		return horaFormat.format(data);
	}
	
	public static Marcacao createFrom(String line){
		String[] fields = line.split(";");
		
		Marcacao marcacao = new Marcacao();
		try {
			marcacao.setData(dateFormat.parse(fields[0]));
			if (fields.length > 1) {
				marcacao.setComentario(fields[1]);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return marcacao;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Marcacao [data=");
		builder.append(dateFormat.format(data));
		builder.append(", comentario=");
		builder.append(comentario);
		builder.append("]");
		return builder.toString();
	}
}
