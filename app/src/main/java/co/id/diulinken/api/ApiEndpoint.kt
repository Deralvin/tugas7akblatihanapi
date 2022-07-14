package co.id.diulinken.api
import co.id.diulinken.model.ResponseCafe
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndpoint {
    @GET("33e36752-e5f1-429f-829b-745f4dfe3d17")
    fun getPeople(): Call<ResponseCafe>
}