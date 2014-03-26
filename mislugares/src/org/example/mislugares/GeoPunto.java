package org.example.mislugares;

public class GeoPunto {
	// declaracion de atributos
	private double longitud, latitud;

	// declaración del constructor
	public GeoPunto(double longitud, double latitud) {
		this.longitud = longitud;
		this.latitud = latitud;
	}

	// declaracion de metodos
	/**
	 * Metodo que devuelve un String concatenando la longitud y la latitud
	 */
	public String toString() {
		return ("Longitud: " + longitud + "\nLatitud: " + latitud);
	}

	/**
	 * Metodo que calcula la distancia entre 2 coordenadas (longitud y latitud)
	 * 
	 * @param punto
	 *            Coordenada (longitud y latitud) destino
	 * @return Devuelve la distancia en una variable double
	 */
	public double distancia(GeoPunto punto) {
		final double RADIO_TIERRA = 6371000; // en metros
		double dLat = Math.toRadians(latitud - punto.latitud);
		double dLon = Math.toRadians(longitud - punto.longitud);
		double lat1 = Math.toRadians(punto.latitud);
		double lat2 = Math.toRadians(latitud);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2)
				* Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return c * RADIO_TIERRA;
	}
}
