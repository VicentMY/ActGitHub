
/*
*
* - Trabajando con fechas
* 
* - Este programa sirve para hacer diferentes operaciones con fechas. Entre ellas se encuentran:
*     obtener la fecha actual.
*     obtener la hora actual.
*     sumar días a la fecha actual.
*     calcular la diferencia en dias entre dos fechas.
*     comprobar si la fecha introducida es anterior o posterior a la fecha actual.
* 
* @version: v1
* 
* @date: 20/11/2023
* 
* @author: Vicent Martínez (VicentMY en GitHub)
* 
*/


import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TrabajandoConFechas {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int opcion;
		boolean continuar = true;
		int dias;
		String fechaDif1;
		String fechaDif2;
		String diferencia;

		do {

			System.out.println("Elige una opción:");
			System.out.println("1.- Obtener la fecha actual");
			System.out.println("2.- Obtener la hora actual");
			System.out.println("3.- Sumar días a la fecha actual");
			System.out.println("4.- Calcular la diferencia en dias entre dos fechas");
			System.out.println("5.- Comprobar si la fecha a introducir es anterior o posterior a la actual");
			System.out.println("9.- Salir del programa");

			opcion = sc.nextInt();

			switch (opcion) {
			case 1:

				System.out.printf("La fecha actual es: %s\n\n", obtenerFechaActual());
				break;

			case 2:
				System.out.printf("La hora actual es: %s.\n\n", obtenerHoraActual());
				break;

			case 3:
				System.out.println("¿Cuantos días quieres sumar?");
				dias = sc.nextInt();

				System.out.printf("En %d dias estaremos a %s.\n\n", dias, sumarDias(dias));
				break;

			case 4:
				sc.nextLine();
				System.out.println("Introduce la primera fecha: (Formato: dia/mes/año)");
				fechaDif1 = sc.nextLine();
				System.out.println("Introduce la segunda fecha: (Formato: dia/mes/año)");
				fechaDif2 = sc.nextLine();

				diferencia = diferenciaDias(fechaDif1, fechaDif2);

				System.out.printf("%10s | %10s | %10s\n", "fecha inicio", "fecha fin", "diferencia");
				System.out.printf("%10s | %10s | %10s\n\n", fechaDif1, fechaDif2, diferencia);
				break;

			case 5:
				sc.nextLine();
				System.out.println("Introduce una fecha para comparar con la actual: (formato: dia/mes/año)");
				fechaDif2 = sc.nextLine();

				fechaDif1 = obtenerFechaActual();

				if (fechaDif2.equals(fechaDif1)) {
					System.out.printf("%s\n\n", "La fecha introducida no puede ser la misma que la actual.");
				} else {
					if (esPosterior(fechaDif1, fechaDif2)) {
						System.out.printf("%s\n\n", "La fecha introducida es posterior a la fecha actual.");
					} else {
						System.out.printf("%s\n\n", "La fecha introducida es anterior a la fecha actual.");
					}
				}
				break;

			case 9:
				continuar = false;
				break;

			default:
				System.out.println(
						"El número introducido no pertenece a ninguna opción del programa. Vuelve a intentarlo.");
				System.out.println();
				break;
			}

		} while (continuar);

		System.out.println("¡Hasta luego!");

		sc.close();
	}

	public static String obtenerFechaActual() {

		DateTimeFormatter formato = DateTimeFormatter.ofPattern("EEEE',' dd 'de' MMMM 'de' yyyy");
		LocalDate fecha = LocalDate.now();

		String fechaFormato = fecha.format(formato);

		return fechaFormato;
	}

	public static String obtenerHoraActual() {

		DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime hora = LocalTime.now();

		String horaFormato = hora.format(formato);

		return horaFormato;
	}

	public static String sumarDias(int dias) {

		DateTimeFormatter formato = DateTimeFormatter.ofPattern("EEEE',' dd 'de' MMMM 'de' yyyy");
		LocalDate resultado;
		String resultadoFormato;

		resultado = LocalDate.now().plusDays(dias);
		resultadoFormato = resultado.format(formato);

		return resultadoFormato;
	}

	public static String diferenciaDias(String fechaDif1, String fechaDif2) {

		Period diferencia;
		LocalDate fechaIni;
		LocalDate fechaFin;
		int dias;
		int meses;
		int años;
		String resultado;

		fechaIni = LocalDate.parse(fechaDif1, DateTimeFormatter.ofPattern("d/M/yyyy"));
		fechaFin = LocalDate.parse(fechaDif2, DateTimeFormatter.ofPattern("d/M/yyyy"));

		diferencia = Period.between(fechaIni, fechaFin);

		dias = diferencia.getDays();
		meses = diferencia.getMonths();
		años = diferencia.getYears();

		resultado = años + " años, " + meses + " meses, " + dias + " dias";

		return resultado;
	}

	public static boolean esPosterior(String fechaActual, String fechaIntroducida) {

		boolean posterior;
		boolean igual;
		long diferencia;
		LocalDate fecha1;
		LocalDate fecha2;

		fecha1 = LocalDate.parse(fechaActual, DateTimeFormatter.ofPattern("EEEE',' d 'de' MMMM 'de' yyyy"));
		fecha2 = LocalDate.parse(fechaIntroducida, DateTimeFormatter.ofPattern("d/M/yyyy"));

		diferencia = ChronoUnit.DAYS.between(fecha1, fecha2);

		if (diferencia > 0) {
			posterior = true;
		} else {
			posterior = false;
		}

		return posterior;
	}

}
