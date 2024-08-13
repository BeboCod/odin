package com.example.odin.utils

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey

object Constants {
    const val DISCORD_URL = "https://discord.com/"
    const val HTTPS_ODIN = "http://10.0.2.2:8080/odin/api/v0"
    val materias = listOf(
        "Algoritmos y Estructuras de Datos",
        "Programación Orientada a Objetos",
        "Sistemas Operativos",
        "Redes de Computadoras",
        "Arquitectura de Computadoras",
        "Bases de Datos",
        "Ingeniería de Software",
        "Lenguajes de Programación",
        "Análisis y Diseño de Sistemas",
        "Seguridad Informática",
        "Inteligencia Artificial",
        "Aprendizaje Automático",
        "Minería de Datos",
        "Procesamiento de Imágenes",
        "Computación Gráfica",
        "Sistemas Distribuidos",
        "Computación en la Nube",
        "Desarrollo de Aplicaciones Web",
        "Desarrollo de Aplicaciones Móviles",
        "Diseño de Interfaces de Usuario",
        "Sistemas Embebidos",
        "Robótica",
        "Criptografía",
        "Redes Neuronales",
        "Computación Cuántica",
        "Ciencia de Datos",
        "Algoritmos Avanzados",
        "Modelado y Simulación",
        "Gestión de Proyectos de Software",
        "Sistemas de Información Geográfica",
        "Bioinformática",
        "Teoría de la Computación",
        "Lenguajes Formales y Autómatas",
        "Matemáticas Discretas",
        "Cálculo Diferencial e Integral",
        "Álgebra Lineal",
        "Estadística y Probabilidad",
        "Investigación de Operaciones",
        "Métodos Numéricos",
        "Sistemas Digitales",
        "Microcontroladores",
        "Electrónica Digital",
        "Circuitos Eléctricos",
        "Señales y Sistemas",
        "Comunicación de Datos",
        "Redes Inalámbricas",
        "Desarrollo de Videojuegos",
        "Sistemas de Tiempo Real",
        "Ingeniería del Conocimiento",
        "Sistemas Expertos",
        "Bases de Datos NoSQL",
        "Programación Funcional",
        "Programación Concurrente",
        "Diseño de Compiladores",
        "Paradigmas de Programación",
        "Programación en Paralelo",
        "Auditoría de Sistemas",
        "Gestión de la Calidad del Software",
        "Administración de Sistemas",
        "Arquitectura de Software",
        "Diseño de Sistemas de Información",
        "Sistemas de Información Empresarial",
        "Sistemas de Control",
        "Sistemas de Energía",
        "Optimizaciones de Software",
        "Desarrollo Ágil de Software",
        "Administración de Servidores",
        "Mantenimiento de Software",
        "Big Data",
        "Realidad Aumentada",
        "Realidad Virtual",
        "Sistemas Ciberfísicos",
        "Internet de las Cosas (IoT)",
        "Computación Ubicua",
        "Tecnologías Emergentes",
        "Ética en la Ingeniería de Software",
        "Economía de la Información",
        "Emprendimiento Tecnológico",
        "Mercadotecnia Digital",
        "Gestión de Riesgos en TI",
        "Administración de Redes",
        "Seguridad en Redes",
        "Protocolos de Comunicación",
        "Taller de Programación",
        "Taller de Desarrollo de Software",
        "Laboratorio de Redes",
        "Laboratorio de Sistemas Operativos",
        "Laboratorio de Electrónica",
        "Ingeniería de Requisitos",
        "Evaluación y Selección de Software",
        "Estrategias de Implementación de Sistemas",
        "Tecnologías Web",
        "Administración de Bases de Datos",
        "Control de Versiones",
        "Desarrollo de Software Seguro",
        "Evaluación de Desempeño de Sistemas",
        "Metodologías de Investigación en Computación",
        "Proyecto Final de Carrera",
        "Seminario de Innovación Tecnológica",
        "Diseño y Desarrollo de Aplicaciones Inteligentes"
    )
}