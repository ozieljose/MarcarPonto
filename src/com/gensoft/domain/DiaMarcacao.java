package com.gensoft.domain;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DiaMarcacao{
	
	private static DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
	
	private Date diaData; 
	private List<Marcacao> marcacoes = new ArrayList<Marcacao>();

	public List<Marcacao> getMarcacoes() {
		return marcacoes;
	}

	public boolean addMarcacao(Marcacao marcacao){
		if (marcacoes.size() == 0) {
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(marcacao.getData());
			
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			
			diaData = calendar.getTime();
			marcacoes.add(marcacao);
			
		}else if(diaData != null){
			
			Calendar calendarDia = Calendar.getInstance();
			calendarDia.setTime(diaData);
			
			Calendar calendarData = Calendar.getInstance();
			calendarData.setTime(marcacao.getData());
			
			boolean mesmoDia = calendarDia.get(Calendar.YEAR) == calendarData.get(Calendar.YEAR);
			mesmoDia = mesmoDia && calendarDia.get(Calendar.MONTH) == calendarData.get(Calendar.MONTH);
			mesmoDia = mesmoDia && calendarDia.get(Calendar.DAY_OF_MONTH) == calendarData.get(Calendar.DAY_OF_MONTH);
			
			if (mesmoDia) {
				marcacoes.add(marcacao);
			}else{
				return false;
			}
		}
		
		return true;
	}

	public Date getDiaData() {
		return diaData;
	}

	public void setDiaData(Date diaData) {
		this.diaData = diaData;
	}
	
	public boolean estaCompleto() {
		return marcacoes != null && marcacoes.size() > 0 && marcacoes.size()%2 == 0;
	}
	
	public String formatDiaData() {
		return dateFormat.format(diaData);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DiaMarcacao [diaData=");
		builder.append(formatDiaData());
		builder.append(", marcacoes=");
		builder.append(marcacoes);
		builder.append("]");
		return builder.toString();
	}

}
