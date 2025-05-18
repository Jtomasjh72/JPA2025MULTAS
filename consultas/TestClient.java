
/**
 * Test adicional: verificarConductorSinIncidencias
 * -------------------------------------------------
 * 
 * Nombre: Verificación de conductores sin incidencias
 * Función: Este test recorre todos los vehículos y sus conductores
 *          para identificar aquellos que no tienen ninguna incidencia registrada.
 *          Su propósito es confirmar que el sistema reconoce correctamente los
 *          conductores sin sanciones y que sus puntos están en estado válido.
 * Autor: JOSÉ TOMÁS JIMÉNEZ HERRERA X3617751N
 * Fecha de implementación: 18 de mayo de 2025
 *
 * Observaciones:
 * - Este test no modifica los datos, solo consulta.
 * - Ayuda a validar que el grafo de entidades y la carga de incidencias funcionan correctamente.
 */
private static void verificarConductorSinIncidencias() {
    System.out.println("Verificando conductor sin incidencias...");
    Servicio servicio = new ServicioImpl();
    List<Vehiculo> vehiculos = servicio.consultarVehiculos();

    for (Vehiculo v : vehiculos) {
        for (Conductor c : v.getConductores()) {
            if (c.getIncidencias().isEmpty()) {
                System.out.println("✔️  Conductor sin incidencias: " + c.getNombre() + " (" + c.getNif() + ") tiene " + c.getPuntos() + " puntos");
            }
        }
    }
}
