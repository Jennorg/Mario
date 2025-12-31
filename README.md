# Mario Bros - NES

Una simulación de Mario Bros para NES desarrollada en Java con JavaFX para la reproducción de audio.

## 🎮 Características

- Juego de plataformas estilo Mario Bros clásico
- Controles con WASD (movimiento clásico)
- Sistema de audio implementado con JavaFX
- Interfaz gráfica desarrollada con Swing
- Proyecto universitario completo

## 📋 Requisitos del Sistema

- **Java 20** o superior
- **JavaFX SDK 21.0.8** o compatible
- Sistema operativo compatible con JavaFX (Windows, macOS, Linux)

## �� Instalación y Configuración

### 1. Instalar JavaFX

JavaFX no está incluido por defecto en las versiones modernas de Java. Necesitas descargarlo e instalarlo:

1. Descarga JavaFX SDK desde [https://openjfx.io/](https://openjfx.io/)
2. Extrae el SDK en una carpeta (ej: `/home/usuario/opt/javafx-sdk-21.0.8/`)

````

### 2. Configurar Variables de Entorno

Asegúrate de que JavaFX esté en tu `PATH` o configura las variables de entorno:

```bash
# Linux/macOS
export PATH_TO_FX="/ruta/a/javafx-sdk-21.0.8/lib"
export JAVA_OPTS="--module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml,javafx.media"
````

### 3. Compilar el Proyecto

```bash
# Compilar (compila todo el proyecto incluyendo subdirectorios)
mkdir -p build/classes
find src -name "*.java" > sources.txt
javac --module-path "$PATH_TO_FX" \
      --add-modules javafx.controls,javafx.media \
      -cp ".:$PATH_TO_FX/*" \
      -d build/classes @sources.txt

# Crear JAR
jar cfm dist/superMario.jar manifest.mf -C build/classes .
```

## ▶️ Ejecutar el Programa

### Método 1: Desde línea de comandos

```bash
# Ejecutar (clases compiladas)
# Asegúrate de exportar PATH_TO_FX a la ruta correcta al SDK (ej: /home/jenorg/opt/javafx-sdk-21.0.8/lib)
java --module-path "$PATH_TO_FX" \
     --add-modules javafx.controls,javafx.fxml,javafx.media \
     -cp "build/classes" game.SuperMario
```

### Método 2: Usando el JAR

```bash
java --module-path "$PATH_TO_FX" \
     --add-modules javafx.controls,javafx.fxml,javafx.media \
     -jar dist/superMario.jar
```

### Método 3: En NetBeans

1. Abre el proyecto en NetBeans
2. Asegúrate de que JavaFX esté configurado en las propiedades del proyecto
3. Ejecuta el proyecto normalmente

### Método 4: En VS Code

El proyecto incluye configuración para VS Code en `.vscode/launch.json`:

```json
{
  "vmArgs": "--module-path /home/jenorg/opt/javafx-sdk-21.0.8/lib --add-modules javafx.controls,javafx.fxml,javafx.media"
}
```

### Scripts de ayuda (recomendado)

He añadido dos scripts en la raíz del proyecto que reproducen exactamente los comandos que te funcionaron:

- `build.sh`: compila todo el proyecto y genera `dist/superMario.jar`.
- `run.sh`: ejecuta la clase principal con clases compiladas o el JAR (`./run.sh` o `./run.sh jar`).

Úsalos así:

```bash
# Exporta la ruta al SDK de JavaFX (ajusta si es necesario)
export PATH_TO_FX="/home/jenorg/opt/javafx-sdk-21.0.8/lib"

# Compilar
./build.sh

# Ejecutar usando clases compiladas
./run.sh

# Ejecutar usando JAR
./run.sh jar
```

> Consejo: si ves "Module javafx.controls not found" verifica que `PATH_TO_FX` apunte a la carpeta `lib` del JavaFX SDK y que contenga los JARs de JavaFX.

## 🎵 Funcionalidad de Audio con JavaFX

El proyecto utiliza JavaFX específicamente para la reproducción de audio:

- **Clase `Reproductor`**: Maneja la reproducción de archivos de audio usando `javafx.scene.media.AudioClip`
- **Módulos requeridos**: `javafx.media` para funcionalidad de audio
- **Formatos soportados**: MP3, WAV, y otros formatos compatibles con JavaFX

### Ejemplo de uso del reproductor:

```java
Reproductor reproductor = new Reproductor();
reproductor.openFile("mario_theme", "src/res/audios/Mario Bros - Log in Menu.mp3");
reproductor.play("mario_theme");
```

## 🎮 Controles

- **W**: Saltar
- **A**: Mover izquierda
- **S**: Agacharse
- **D**: Mover derecha

## 📁 Estructura del Proyecto

```
src/
├── game/
│   ├── SuperMario.java          # Clase principal
│   ├── MarioLogin.java          # Interfaz de login
│   ├── Entidades/               # Entidades del juego
│   ├── Graficos/                # Componentes gráficos
│   ├── Objeto/                  # Objetos del juego
│   └── manager/
│       ├── GamePanel.java       # Panel principal del juego
│       └── Reproductor.java     # Reproductor de audio (JavaFX)
└── res/
    ├── images/                  # Recursos gráficos
    └── audios/                  # Archivos de audio
```

## ⚠️ Solución de Problemas

### Error: "JavaFX runtime components are missing"

```bash
# Asegúrate de incluir los módulos de JavaFX
--add-modules javafx.controls,javafx.fxml,javafx.media
```

### Error: "Module not found: javafx.media"

```bash
# Verifica que la ruta a JavaFX sea correcta
--module-path /ruta/correcta/a/javafx-sdk/lib
```

### Audio no reproduce

1. Verifica que los archivos de audio estén en `src/res/audios/`
2. Asegúrate de que el módulo `javafx.media` esté incluido
3. Comprueba que los formatos de audio sean compatibles

## 🛠️ Desarrollo

Este proyecto fue desarrollado como proyecto universitario y utiliza:

- **Java Swing** para la interfaz gráfica
- **JavaFX** para la reproducción de audio
- **Java 20** como versión base
- **NetBeans** como IDE principal

## 📝 Notas

- El sistema de login está comentado en el código principal
- Para habilitar el login, descomenta las líneas correspondientes en `SuperMario.java`
- Los recursos (imágenes y audio) deben estar en las rutas especificadas
