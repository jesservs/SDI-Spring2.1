package Util;

public class OrionUtil {
	
	/*
	 * Converter datas de yyyy-mm-dd para dd/mm/yyyy
	 */
	public static final String convertDataSqlToPt(String data){
		String[] fatias = data.split("-");
		String ano = fatias[0];
		String mes = fatias[1];
		String dia = fatias[2];
		return dia + "/" + mes + "/" + ano;
	}

}
