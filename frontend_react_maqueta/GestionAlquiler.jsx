import React, { useState, useEffect } from 'react';

/**
 * Componente principal para la gestión de alquileres de andamios.
 * @author Yann Valen
 * @version 1.0
 */
const GestionAlquiler = () => {
  // Estado para almacenar la lista de alquileres
  const [alquileres, setAlquileres] = useState([]);

  // Efecto para simular la carga de datos desde la API Java
  useEffect(() => {
    // Aquí iría el fetch("http://localhost:8080/AlquilerServlet")
    const datosPrueba = [
      { id: 1, cliente: "Juan Perez", estado: "PENDIENTE", total: 150000 },
      { id: 2, cliente: "Maria Lopez", estado: "PAGADO", total: 85000 }
    ];
    setAlquileres(datosPrueba);
  }, []);

  return (
    <div className="container">
      <h2>🏗️ Panel de Control - Andamios Viviescas</h2>
      
      {/* Tabla de Alquileres */}
      <table className="table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Cliente</th>
            <th>Total</th>
            <th>Estado</th>
          </tr>
        </thead>
        <tbody>
          {alquileres.map((alq) => (
            <tr key={alq.id}>
              <td>{alq.id}</td>
              <td>{alq.cliente}</td>
              <td>${alq.total}</td>
              <td>
                {/* Lógica condicional para el estilo del badge */}
                <span className={alq.estado === 'PAGADO' ? 'badge-green' : 'badge-red'}>
                  {alq.estado}
                </span>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default GestionAlquiler;