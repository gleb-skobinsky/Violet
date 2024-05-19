import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import kotlin.io.path.Path


abstract class OpenApiGenerationTask : DefaultTask() {

    init {
        this.group = "openAPIAutomation"
    }

    private val projectRoot = project.rootProject.projectDir.absolutePath

    private val specPath = Path(
        projectRoot,
        "backend",
        "src",
        "main",
        "resources",
        "openapi"
    )

    @TaskAction
    fun fetchAndPrintJson() {
        val url = URL("http://0.0.0.0:8080/openapi.json")
        val connection = url.openConnection() as HttpURLConnection

        try {
            connection.requestMethod = "GET"
            connection.connect()

            val responseCode = connection.responseCode

            if (responseCode == HttpURLConnection.HTTP_OK) {
                val jsonResponse = connection.inputStream.bufferedReader().use { it.readText() }
                val yaml = jsonResponse.asYaml()
                val targetDir = File(specPath.toUri())
                if (targetDir.exists()) {
                    val targetFile = File(targetDir, "openapi.yaml")
                    targetFile.writeText(yaml)
                } else {
                    println("Target file does not exist")
                }
            } else {
                println("Failed to fetch JSON. HTTP response code: $responseCode")
            }
        } catch (e: Exception) {
            println("Error occurred: ${e.message}")
        } finally {
            connection.disconnect()
        }
    }

    @Throws(JsonProcessingException::class, IOException::class)
    fun String.asYaml(): String {
        // parse JSON
        val jsonNodeTree: JsonNode = ObjectMapper().readTree(this)
        deleteNulls(jsonNodeTree)
        // save it as YAML
        val jsonAsYaml = YAMLMapper().writeValueAsString(jsonNodeTree)
        return jsonAsYaml
    }

    private fun deleteNulls(jsonNode: JsonNode) {
        jsonNode.removeAll { it.isNull }
        for (node in jsonNode.elements()) {
            deleteNulls(node)
        }
    }
}
