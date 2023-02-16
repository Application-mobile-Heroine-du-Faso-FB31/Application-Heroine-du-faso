//***************************************************************************************/
//        *    Title: Store Firebase Realtime Database in Android Studio 2021 | Firebase Android CRUD Operation
//        *    Author: Cambo Tutorial
//        *    Date: 2020
//        *    Code version: 1.0
//        *    Availability: https://www.youtube.com/watch?v=741QCymuky4&t=416s
//        *
//        ***************************************************************************************/

package com.example.heroinedufaso;


import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAO {

    private DatabaseReference databaseReference;

    public DAO(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Manager.class.getSimpleName());
    }

    public Task<Void> add(Manager manager){
        return databaseReference.push().setValue(manager);
    }

    public Task<Void> add(User user){
        return databaseReference.push().setValue(user);
    }
}
