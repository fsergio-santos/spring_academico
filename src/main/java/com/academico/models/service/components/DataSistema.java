package com.academico.models.service.components;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Component;

import com.academico.config.SpringProjectConfig;


@Component
public class DataSistema {
	
	
	public DataSistema() {
		
	}

	
	public Date now() {
		return new Date();
	}
	
	
	public Date dateSum(Date data ) {  
		Calendar calendario = new GregorianCalendar();
	    calendario.setTime(data);
	    calendario.add(Calendar.DAY_OF_MONTH, + 30 );
		return calendario.getTime(); 
	}
	
	
	public Date renovateTokenFromAccessUser( int tempo ) {  
		Calendar calendario = Calendar.getInstance();
	    calendario.setTime(new Date());
	    calendario.add(Calendar.MILLISECOND, tempo );
		return calendario.getTime(); 
	}
	
	
	public int totalDiasEntreDatas(Date dataInicial, Date dataFinal) {
		int totalDias = (int) ((dataFinal.getTime() - dataInicial.getTime()) / getTotalDias() );
		return totalDias;
	}
	
	public Date validadeAccessRefreshToken(Date data, int tempo) {
		return new Date(( data.getTime() * getDias() * tempo));
	}
		
	
	private int getTotalDias() {
		return ( getMilisegundos() * getSegundos() * getHoras() * getDias() );
	}
	
	
	private int getMilisegundos() {
		return SpringProjectConfig.MILISEGUNDOS;
	}
	
	private int getSegundos() {
		return SpringProjectConfig.SEGUNDOS;
	}
	
	private int getHoras() {
		return SpringProjectConfig.HORAS;
	}
	
	private int getDias() {
		return SpringProjectConfig.DIA;
	}
	
	
}




//long diferenca = System.currentTimeMillis() - dataInicial.getTimeInMillis();          
//long diferencaSeg = diferenca /1000;    //DIFERENCA EM SEGUNDOS 
//long diferencaMin = diferenca /(60*1000);    //DIFERENCA EM MINUTOS 
//long diferencaHoras = diferenca/(60*60*1000);    // DIFERENCA EM HORAS  
