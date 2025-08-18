# 🧪 Bioreactor Control System

Este proyecto es una aplicación en **Java (Swing + MVC)** para el control, supervisión y monitoreo de un **biorreactor de laboratorio/industria**.  
Incluye control de **temperatura, pH, oxígeno disuelto (OD), agitación, presión y procesos de esterilización**, así como la gestión de periféricos (válvulas, bombas, calefactores y ventilación).

---

## ✨ Características principales

- **Interfaz gráfica (GUI en Swing)**:
  - Panel de control en tiempo real.
  - Indicadores de estado (niveles, presiones, temperaturas).
  - Alarmas visuales en caso de pérdida de comunicación o valores fuera de rango.
  
- **Controladores implementados**:
  - 🔥 **Controlador de temperatura**  
    Con soporte para:
    - Intercambiador de calor (agua).  
    - Resistencia eléctrica con control proporcional (tipo PWM por ciclos).  
    - Ajuste dinámico de ganancia, integral y derivativo.
    
  - 🧼 **Controlador de esterilización**  
    - Manejo automático de ciclos de **esterilización con vapor**.  
    - Detección de fin de proceso y desfogue.  
    - Purga automática de aire por pulsos controlados.  

- **Gestión de sensores y actuadores**:
  - Sensores: temperatura, presión, pH, OD, niveles alto/medio/bajo.  
  - Actuadores: válvulas de vapor, válvula de desfogue, bombas, resistencias, ventiladores.  

- **Comunicación**:
  - Módulo de comunicación con hardware (interfaz serie/USB o red).  
  - Validación de conexión y recuperación ante fallos.  

- **Arquitectura**:
  - Patrón **MVC (Modelo - Vista - Controlador)**.  
  - Clases específicas para cada controlador de proceso.  
  - Parámetros configurables (setpoints, histeresis, desvíos, ciclos, etc.).

---

## 📂 Estructura del proyecto
src/
├── com.controller/
│ ├── ControladorEsterilizacion.java # Lógica de ciclos de esterilización
│ ├── ControladorTemperatura.java # Lógica de control térmico
│ └── ...
│
├── com.model/
│ ├── Bioreactor.java # Modelo principal del biorreactor
│ ├── Parametros.java # Parámetros configurables
│ └── ...
│
├── com.views/
│ ├── InterfazPrincipal.java # Vista principal de la aplicación
│ ├── Control.java # Panel de control
│ ├── TestComponentes.java # Panel de pruebas
│ └── ...
│
└── com.control/
└── Variables.java # Variables globales del sistema


---

## ⚙️ Requisitos

- **Java 7 o superior** (probado en JDK 7, 8 y 11).  
- **NetBeans IDE** o cualquier IDE compatible (IntelliJ, Eclipse).  
- **Git** para control de versiones.  
- (Opcional) **Hardware real** conectado al PC para pruebas completas.

---

📜 Licencia

Este proyecto se distribuye bajo la licencia MIT.
Puedes usarlo, modificarlo y compartirlo libremente siempre que mantengas la atribución.

