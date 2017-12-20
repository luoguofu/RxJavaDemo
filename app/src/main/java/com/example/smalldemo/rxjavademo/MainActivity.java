package com.example.smalldemo.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button rxJavaBt,rxjavaJust,rxjavaForm,rxjavaAtion,rxjavaMap,rxjavaFlatmap,rxjavaSchedule;
    public final static String TAG = "rxjava";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rxJavaBt = (Button) findViewById(R.id.rxjava_creat);
        rxjavaJust = (Button) findViewById(R.id.rxjava_just);
        rxjavaForm = (Button) findViewById(R.id.rxjava_form);
        rxjavaAtion = (Button) findViewById(R.id.rxjava_action);
        rxjavaMap = (Button) findViewById(R.id.rxjava_map);
        rxjavaFlatmap = (Button) findViewById(R.id.rxjava_flatmap);
        rxjavaSchedule = (Button) findViewById(R.id.rxjava_schedule);
        rxJavaBt.setOnClickListener(this);
        rxjavaJust.setOnClickListener(this);
        rxjavaForm.setOnClickListener(this);
        rxjavaAtion.setOnClickListener(this);
        rxjavaMap.setOnClickListener(this);
        rxjavaFlatmap.setOnClickListener(this);
        rxjavaSchedule.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rxjava_creat:
                rxjavaCreat();
//                rxjavaJust();
//                rxjavaForm();
//                rxjavaAtion();
//                rxjavaMap();
//                rxjavaFlatmap();
//                rxjavaSchedule();
                break;
            case R.id.rxjava_just:
                rxjavaJust();
                break;
            case R.id.rxjava_form:
                rxjavaForm();
                break;
            case R.id.rxjava_action:
                rxjavaAtion();
                break;
            case R.id.rxjava_map:
                rxjavaMap();
                break;
            case R.id.rxjava_flatmap:
                rxjavaFlatmap();
                break;
            case R.id.rxjava_schedule:
                rxjavaSchedule();
                break;
            default:
                break;
        }
    }

    private void rxjavaCreat() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s);

            }
        };
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {     //Observable的creat创建方式
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello");
                subscriber.onNext("world");
                subscriber.onCompleted();
            }

        });
        observable.subscribe(observer);
    }

    private void rxjavaJust() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s);

            }
        };
        Observable observable = Observable.just("hello2", "world2");
        observable.subscribe(observer);
    }

    private void rxjavaForm() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s);

            }
        };
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s);

            }
        };
        String array[] = {"hello3", "world3"};
        ArrayList<String> list = new ArrayList<>();
        list.add("hello4");
        list.add("world4");
        Observable observable = Observable.from(list);
        observable.subscribe(subscriber);
    }

    private void rxjavaAtion() {

        Observable observable = Observable.just("hello5", "world5");
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e(TAG, "onNext: " + s);
            }
        });
    }

    private void rxjavaMap() {
        Student student1 = new Student();
        student1.setName("xiaoming");
        Student student2 = new Student();
        student2.setName("xiaohong");
        Student student3 = new Student();
        student3.setName("xiaohua");
        Observable observable = Observable.just(student1, student2, student3);
        observable.map(new Func1<Student, String>() {       //map和Action的区别在于map有返回值
            @Override
            public String call(Student student) {
                Log.e(TAG, "student: " + student.getName());
                return student.getName();
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e(TAG, "onNext: " + s);
            }
        });
    }

    private void rxjavaFlatmap() {
        List<Student> studentList = new ArrayList<>();
        Student student1 = new Student();
        Student student2 = new Student();
        List<Course> list = new ArrayList<>();
        list.add(new Course("xiaoming"));
        list.add(new Course("xiaohong"));
        list.add(new Course("xiaohua"));
        student1.setCourseList(list);
        student2.setCourseList(list);
        studentList.add(student1);
        studentList.add(student2);
        final Observable observable = Observable.from(studentList);
        observable.flatMap(new Func1<Student, Observable<Course>>() {

            @Override
            public Observable<Course> call(Student student) {
                return Observable.from(student.getCourseList());
            }
        }).subscribe(new Action1<Course>() {
            @Override
            public void call(Course course) {
                Log.e(TAG, "onNext: " + course.getName());
            }
        });
    }

    private void rxjavaSchedule() {
        Student student1 = new Student();
        student1.setName("xiaoming");
        Student student2 = new Student();
        student2.setName("xiaohong");
        Student student3 = new Student();
        student3.setName("xiaohua");
        Observable.just(student1, student2, student3)
                .subscribeOn(Schedulers.newThread())   //事件在新线程产生
                .observeOn(Schedulers.io())          //在io线程处理数据
                .map(new Func1<Student, String>() {       //map和Action的区别在于map有返回值
                    @Override
                    public String call(Student student) {
                        return student.getName();            //处理数据
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())     //在主线程中消费事件
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e(TAG, "onNext: " + s);      //消费事件
                    }
                });
    }

}
