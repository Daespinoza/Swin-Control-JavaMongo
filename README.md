# Swin-Control (JavaMongo)

**Swin-Control** es un sistema digital para la **gestión y control de las clases de natación**, diseñado para reemplazar el control manual y en hojas de Excel por una interfaz más amigable, especializada y eficiente.

## 🎯 Objetivo del proyecto

El objetivo principal es crear una aplicación de escritorio que permita gestionar de manera integral las actividades de una academia de natación, brindando funcionalidades específicas que faciliten la administración de nadadores, clases, pagos y asistencia.

## 📝 Funcionalidades principales

- Registrar nuevos nadadores.
- Asignar clases con día y hora específicos.
- Registrar clases en un calendario visual.
- Modificar clases en caso de que un nadador no pueda asistir.
- Implementar un sistema sencillo de control de pagos:
  - Saber quién ha pagado.
  - Quién ha pagado por adelantado.
  - Quién tiene pagos pendientes.
- Manejar paquetes de clases.
- Actualizar el estado de asistencia:
  - Asistió.
  - No asistió pero avisó (modificación).
  - No asistió sin aviso.
- Generar reportes de asistencia y reportes económicos.

## 💻 Tecnologías

El proyecto está desarrollado con las siguientes tecnologías:

- **Java (última versión estable)**
- **MongoDB (instalación local en el dispositivo del usuario)**
- **Librería gráfica:** Se seleccionará la librería con **mayor documentación y soporte disponible** (por ejemplo, **Swing** o **JavaFX**).
- **Maven** (como herramienta de gestión de dependencias y construcción de proyecto, por su simplicidad y amplio soporte).

## ⚙️ Requisitos

Para ejecutar la aplicación se requiere:

- Sistema operativo: **Windows**
- **Java JDK** (última versión estable)
- **MongoDB local** instalado y corriendo en el mismo dispositivo
- No requiere conexión a internet para funcionar
- Exportación e importación de datos mediante archivos **JSON**

## 📦 Instalación

- La aplicación se entregará mediante un **instalador tipo `.exe`**.
- La instalación de **MongoDB** deberá realizarse **aparte**. Se proveerán instrucciones o enlaces para la descarga e instalación.
- La configuración de conexión a la base de datos será **manual**, con asistencia directa para su configuración inicial.
- Se incluirá un archivo **JSON con la estructura básica de la base de datos** para inicializar las colecciones necesarias en MongoDB.

## 🚀 Uso

Una vez instalada la aplicación y configurada la base de datos:

1. Abrir la aplicación desde el acceso directo generado por el instalador.
2. Conectarse a la base de datos MongoDB local (configuración ya realizada previamente).
3. Comenzar a registrar nadadores, clases, pagos y asistencia.

## 🗃️ Exportación e importación de datos

- La aplicación permitirá **exportar los datos completos en formato JSON**.
- Los archivos JSON exportados podrán ser utilizados para **migrar datos entre distintas instalaciones** o como respaldo de seguridad.
- Será posible **importar un archivo JSON previamente exportado**, para restaurar o trasladar datos a otra instancia de la aplicación.

## 📄 Reportes

- La aplicación generará **reportes en formato PDF** para:
  - Asistencia de nadadores.
  - Estado de pagos.
  - Resúmenes económicos.
- Los reportes estarán diseñados para **ser fácilmente leídos e impresos**, con información clara y resumida.
