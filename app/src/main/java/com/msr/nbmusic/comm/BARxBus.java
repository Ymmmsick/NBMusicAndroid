package com.msr.nbmusic.comm;

import com.michaelflisar.rxbus2.RxBus;
import com.michaelflisar.rxbus2.RxBusBuilder;
import com.michaelflisar.rxbus2.rx.RxBusMode;

import io.reactivex.functions.Consumer;

public class BARxBus {

    public static void post(Object obj) {
        RxBus.get().send(obj);
    }

    public static <T> void subscribe(Object object, Class<T> tClass, Consumer<T> consumer) {
        RxBusBuilder.create(tClass)
//                .withQueuing(object)          // optional: if enabled, events will be queued while the IRxBusQueue is paused!
                .withBound(object)            // optional: this binds the subcritpion to this object and you can unsubscribe all bound disposables at once
                .withMode(RxBusMode.Main)   // optional: set the thread to main or background if wanted, events will be emitted on the corresponding thread
                .subscribe(consumer);
    }

}