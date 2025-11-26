# Cosmeteologia

#Link Trello
https://trello.com/b/EewdYaDX/proyecto-app-moviles

# Cosmeteología

Aplicación Android moderna desarrollada en Kotlin y Jetpack Compose diseñada para la gestión de formularios de clientes en cosmetología y el escaneo de productos cosméticos para obtener información detallada.

## 📱 Características Principales

* **Autenticación de Usuarios:** Sistema de Registro e Inicio de Sesión diferenciado para dos roles:
    * **Cliente:** Puede llenar formularios de salud/estilo de vida y escanear productos.
    * **Profesional:** Puede visualizar el listado de clientes y sus fichas técnicas completas.
* **Gestión de Fichas de Clientes:** Formulario completo que recopila:
    * Datos personales y de contacto.
    * Estilo de vida (tabaco, alcohol, deporte).
    * Historial médico (antecedentes, alergias, medicamentos).
    * Foto del cliente.
* **Escáner de Productos:** Utiliza la cámara del dispositivo para escanear códigos de barras de productos cosméticos.
* **Consulta de API:** Integración con la API de **Open Beauty Facts** para mostrar detalles del producto (ingredientes, marca, imagen) tras el escaneo.
* **Persistencia de Datos:** Base de datos local segura utilizando **Room**.

## 🛠 Tecnologías Utilizadas

* **Lenguaje:** [Kotlin](https://kotlinlang.org/)
* **UI Toolkit:** [Jetpack Compose](https://developer.android.com/jetbrains/compose) (Material Design 3)
* **Arquitectura:** MVVM (Model-View-ViewModel)
* **Base de Datos:** [Room Database](https://developer.android.com/training/data-storage/room)
* **Red:** [Retrofit](https://square.github.io/retrofit/) + Gson
* **Carga de Imágenes:** [Coil](https://coil-kt.github.io/coil/)
* **Cámara y ML:**
    * CameraX
    * ML Kit Barcode Scanning
* **Navegación:** Navigation Compose

## 📋 Requisitos Previos

Para ejecutar este proyecto necesitas:

* **Android Studio:** Koala o superior (recomendado).
* **JDK:** Versión 17 o superior.
* **Dispositivo/Emulador:** Android 7.0 (API nivel 24) o superior.

## 🚀 Pasos de Instalación

1.  **Clonar el repositorio:**
    ```bash
    git clone [https://github.com/tu-usuario/cosmeteologia.git](https://github.com/tu-usuario/cosmeteologia.git)
    ```

2.  **Abrir en Android Studio:**
    * Abre Android Studio.
    * Selecciona "Open" y navega hasta la carpeta del proyecto clonado.

3.  **Sincronizar Gradle:**
    * Espera a que Android Studio descargue las dependencias y sincronice el proyecto.
    * Si aparece algún error, verifica tu conexión a internet y la configuración del JDK en `Settings > Build, Execution, Deployment > Build Tools > Gradle`.

4.  **Ejecutar la aplicación:**
    * Conecta un dispositivo Android físico (recomendado para probar la cámara) o inicia un emulador.
    * Presiona el botón **Run** (Triángulo verde) o `Shift + F10`.

## 🔐 Permisos

La aplicación solicitará los siguientes permisos en tiempo de ejecución:

* **Cámara:** Necesario para escanear los códigos de barras de los productos y tomar fotos de perfil en el formulario.
* **Internet:** Necesario para consultar la información del producto en Open Beauty Facts.

## 📂 Estructura del Proyecto

El código está organizado siguiendo la arquitectura limpia y MVVM:

* `auth/`: Pantallas y lógica de Login y Registro.
* `client/`: Pantallas específicas para el usuario Cliente (Dashboard, Perfil).
* `professional/`: Pantallas para el usuario Profesional (Lista de clientes).
* `form/`: Pantalla del formulario de ingreso de datos.
* `scanner/`: Lógica de cámara y detalle de productos.
* `data/`: Entidades de Room, DAOs, Modelos de API y Repositorios.
* `ui/viewmodel/`: ViewModels para manejar el estado de la UI.
* `navigation/`: Configuración del grafo de navegación de la app.

## 📄 API Reference

Este proyecto utiliza la API pública de [Open Beauty Facts](https://world.openbeautyfacts.org/).

---
Desarrollado como proyecto de Cosmeteología.
