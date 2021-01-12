import kotlinx.cinterop.*
import nativecode.TestStruct
import nativecode.invokeFromThread
import platform.posix.sleep
import kotlin.native.concurrent.freeze

fun main(): Unit = autoreleasepool {
    val func = staticCFunction<CValue<TestStruct>, Unit> {
        initRuntimeIfNeeded()
        memScoped {
            println("Hello, Kotlin/Native! ${it.ptr.pointed.d}")
        }
    }.freeze()
    invokeFromThread(func.reinterpret())
    sleep(4u)
}
