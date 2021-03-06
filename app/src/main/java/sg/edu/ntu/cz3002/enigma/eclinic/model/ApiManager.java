package sg.edu.ntu.cz3002.enigma.eclinic.model;

import java.util.List;

import okhttp3.ResponseBody;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Api manager
 */
public class ApiManager {

    private static ApiManager ourInstance = new ApiManager();
    private ApiService _apiService;
    private static final String _url = "http://172.20.25.192:8000/api/";

    public static ApiManager getInstance() {
        return ourInstance;
    }

    private ApiManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(_url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        _apiService = retrofit.create(ApiService.class);
    }

    public Observable<AuthToken> authenticate(String username, String password) {
        return _apiService.authenticate(new User(username, password));
    }

    public Observable<List<Reservation>> getReservation(String patientName){
        return _apiService.getReservation(patientName);
    }

    public Observable<List<Doctor>> getDoctorList(){
        return _apiService.getDoctorList();
    }

    public Observable<ResponseBody> sendReservation(String doctor, String patient, String datetime) {
        return _apiService.sendReservation(new Reservation(doctor, patient, datetime));
    }

    public Observable<ResponseBody> sendProgress(String doctor, String patient, String progress, String datetime) {
        return _apiService.sendProgress(new Progress(doctor, patient, progress, datetime));
    }

    public Observable<User> signup(String username, String password) {
        return _apiService.signup(new User(username, password));
    }

    public Observable<ResponseBody> sendMessage(String msg, String receiver, String sender){
        return _apiService.sendMessage(new Message(sender, receiver, msg));
    }

    public Observable<ResponseBody> sendMessageToken(String username, String token) {
        return _apiService.sendMessageToken(new MessageToken(token, username));
    }

    public Observable<List<Doctor>> testIdentity(String username) {
        return _apiService.testIdentity(username);
    }

    public Observable<List<Progress>> getProgress(String patientName, String doctorName) {
        return _apiService.getProgress(patientName, doctorName);
    }

    public Observable<Patient> registerAsPatient(String username, String gender, int age) {
        return _apiService.registerAsPatient(new Patient(username, gender, age));
    }

    public Observable<Doctor> registerAsDoctor(String username, String gender, String clinic, String description) {
        return _apiService.registerAsDoctor(new Doctor(username, gender, clinic, description));
    }
}
