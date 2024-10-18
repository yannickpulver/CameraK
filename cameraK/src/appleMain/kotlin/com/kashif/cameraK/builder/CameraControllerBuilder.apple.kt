package com.kashif.cameraK.builder


import com.kashif.cameraK.controller.CameraController
import com.kashif.cameraK.enums.CameraLens
import com.kashif.cameraK.enums.Directory
import com.kashif.cameraK.enums.FlashMode
import com.kashif.cameraK.enums.ImageFormat
import com.kashif.cameraK.enums.Rotation
import com.kashif.cameraK.enums.TorchMode
import com.kashif.cameraK.plugins.CameraPlugin
import com.kashif.cameraK.utils.InvalidConfigurationException

/**
 * iOS-specific implementation of [CameraControllerBuilder].
 */
class IOSCameraControllerBuilder : CameraControllerBuilder {

    private var flashMode: FlashMode = FlashMode.OFF
    private var torchMode: TorchMode = TorchMode.OFF
    private var cameraLens: CameraLens = CameraLens.BACK
    private var rotation: Rotation = Rotation.ROTATION_0
    private var imageFormat: ImageFormat? = null
    private var directory: Directory? = null
    private val plugins = mutableListOf<CameraPlugin>()

    override fun setFlashMode(flashMode: FlashMode): CameraControllerBuilder {
        this.flashMode = flashMode
        return this
    }

    override fun setCameraLens(cameraLens: CameraLens): CameraControllerBuilder {
        this.cameraLens = cameraLens
        return this
    }

    override fun setRotation(rotation: Rotation): CameraControllerBuilder {
        this.rotation = rotation
        return this
    }

    override fun setImageFormat(imageFormat: ImageFormat): CameraControllerBuilder {
        this.imageFormat = imageFormat
        return this
    }

    override fun setDirectory(directory: Directory): CameraControllerBuilder {
        this.directory = directory
        return this
    }

    override fun addPlugin(plugin: CameraPlugin): CameraControllerBuilder {
        plugins.add(plugin)
        return this
    }

    override fun build(): CameraController {

        val format = imageFormat ?: throw InvalidConfigurationException("ImageFormat must be set.")
        val dir = directory ?: throw InvalidConfigurationException("Directory must be set.")

        // Initialize the iOS-specific CameraController
        val cameraController = CameraController(
            flashMode = flashMode,
            torchMode = torchMode,
            cameraLens = cameraLens,
            rotation = rotation,
            imageFormat = format,
            directory = dir,
            plugins = plugins
        )

        return cameraController
    }

    override fun setTorchMode(torchMode: TorchMode): CameraControllerBuilder {
        this.torchMode = torchMode
        return this
    }
}
