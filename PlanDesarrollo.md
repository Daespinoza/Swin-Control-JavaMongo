# 🏊‍♂️ Swin-Control

**Swin-Control** es una aplicación de escritorio desarrollada en **Java** con **MongoDB** como base de datos, orientada a la gestión y control de clases de natación. Está diseñada para ser utilizada localmente en **Windows**, sin necesidad de conexión a Internet, y con la posibilidad de exportar los datos en formato **JSON** para migraciones o respaldos.

---

## 🎯 Objetivo principal

El objetivo de Swin-Control es reemplazar el uso actual de hojas de cálculo (Excel) para gestionar las clases de natación, ofreciendo una interfaz más amigable, especializada y robusta.

La aplicación permitirá:

- Registrar nuevos **nadadores**.
- Mostrar las clases registradas en un **calendario visual**.
- Registrar y **asignar clases** (día y hora) a nadadores.
- **Modificar clases** en caso de cambios de horario o ausencias.
- Gestionar un **sistema de pagos sencillo**:
  - Registro de pagos realizados.
  - Estado de cuenta (quién ha pagado, quién adeuda, quién pagó por adelantado).
- Cambiar el estado de asistencia de cada clase:
  - Asistió.
  - No asistió (avisó previamente).
  - No asistió (sin aviso).
- Generar **reportes en PDF**:
  - Reporte de asistencia.
  - Reporte económico.
- Exportar e importar datos en archivos **JSON**.

---

## 🛠️ Tecnologías y herramientas

- **Lenguaje principal:** Java (última versión estable)
- **Base de datos:** MongoDB (instalación local)
- **Gestor de dependencias:** Maven (o Gradle según conveniencia)
- **Interfaz gráfica:** Librería con mayor documentación y soporte (por ejemplo, JavaFX)
- **Reportes PDF:** (pendiente definir librería, sugerencia: iText o JasperReports)
- **Plataforma objetivo inicial:** Windows
- **Instalador final:** ejecutable `.exe` (con base de datos instalada aparte)

---

## 📈 Plan de desarrollo

### 🟢 **Fase 1: funcionalidad mínima (MVP)**

Funcionalidades esenciales para una versión operativa básica:

1. ✅ Crear la **ventana principal** con menú de navegación.
2. ✅ Implementar **registro de nadadores** (agregar, editar, eliminar, listar).
3. ✅ Implementar **registro de clases** (agendar, modificar, cancelar).
4. ✅ Registrar **asistencia** (asistió, avisó, no asistió).
5. ✅ Implementar **registro de pagos básicos** (pago realizado, saldo).
6. ✅ Exportar datos a **JSON** manualmente.

**Objetivo a corto plazo (~4-6 semanas):**  
Contar con una aplicación funcional que permita gestionar nadadores, clases, asistencia, pagos y exportación de datos.

---

### 🟡 **Fase 2: mejoras funcionales y usabilidad**

1. ✅ Implementar **calendario visual** de clases (día, semana, mes).
2. ✅ Generar **reportes en PDF**:
   - Reporte de asistencia.
   - Reporte económico.
3. ✅ Validaciones y controles de datos (campos obligatorios, formatos).
4. ✅ Importación de datos desde **JSON**.

**Objetivo a mediano plazo (~3 meses):**  
Mejorar la experiencia visual, agregar reportes y opciones de migración/importación.

---

### 🟠 **Fase 3: extras y optimización**

1. Sistema de **notificaciones o alertas** (pagos vencidos, clases próximas).
2. **Copia de seguridad automática** en JSON.
3. Opción de **multiusuario** con perfiles.
4. Configuraciones avanzadas (colores, datos de academia, parámetros).

**Objetivo a largo plazo:**  
Hacer la aplicación más robusta, personalizable y adaptable.

---

## 📅 Orden sugerido de desarrollo

1. Estructura general de la aplicación (ventana principal y menú).
2. CRUD de **nadadores**.
3. CRUD de **clases**.
4. Registro de **asistencia**.
5. Registro de **pagos**.
6. Exportación de **JSON**.
7. Calendario visual.
8. Reportes en PDF.

---

## 🚩 Requerimientos iniciales

✅ El usuario no necesita conexión a Internet.  
✅ MongoDB se instalará manualmente en el equipo receptor.  
✅ La migración de datos se realizará mediante exportación/importación de JSON.  
✅ El ejecutable final será distribuido mediante instalador `.exe`.  
✅ Los reportes serán exportados en formato PDF para fácil lectura.

---

## ✍️ Notas adicionales

🔹 La aplicación está pensada para **uso personal de un profesor**, con enfoque en **simplicidad y autonomía**.  
🔹 La estructura inicial de la base de datos podrá incluirse en un archivo JSON para cargar manualmente en MongoDB.

---

## 📌 Estado actual del proyecto

(Completa o actualiza esta sección a medida que avances)

| Funcionalidad            | Estado  |
|-------------------------|---------|
| Ventana principal        | Pendiente |
| Registro de nadadores    | Pendiente |
| Registro de clases       | Pendiente |
| Registro de asistencia    | Pendiente |
| Registro de pagos        | Pendiente |
| Exportación JSON         | Pendiente |
| Calendario visual        | Pendiente |
| Reportes PDF             | Pendiente |

---

## 🧭 Próximos pasos

1. Definir librería de interfaz gráfica.
2. Configurar proyecto Maven o Gradle.
3. Crear estructura inicial de carpetas y paquetes.
4. Comenzar desarrollo de ventana principal y módulo de registro de nadadores.

---

¡Gracias por visitar este proyecto!  
✨ ¡Cualquier sugerencia o contribución será bienvenida!

