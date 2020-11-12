package com.shindra;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J$\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0006J$\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\rJ\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/shindra/MyViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "ctsRepository", "Lcom/shindra/ctslibrary/repository/CtsRepository;", "estimatedTimeTable", "Lio/reactivex/rxjava3/subjects/BehaviorSubject;", "Lcom/shindra/ctslibrary/bo/EstimatedTimeTable;", "routeType", "Lcom/shindra/ctslibrary/bo/RouteType;", "lineRef", "", "direction", "", "linesDelivery", "Lcom/shindra/ctslibrary/bo/Lines;", "stopPoints", "Lcom/shindra/ctslibrary/bo/Stops;", "latitude", "", "longitude", "searchAroundInM", "veloParcs", "Lcom/shindra/ctslibrary/bo/VeloParcs;", "app_debug"})
public final class MyViewModel extends androidx.lifecycle.ViewModel {
    private final com.shindra.ctslibrary.repository.CtsRepository ctsRepository = null;
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.rxjava3.subjects.BehaviorSubject<com.shindra.ctslibrary.bo.VeloParcs> veloParcs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.rxjava3.subjects.BehaviorSubject<com.shindra.ctslibrary.bo.Stops> stopPoints(double latitude, double longitude, int searchAroundInM) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.rxjava3.subjects.BehaviorSubject<com.shindra.ctslibrary.bo.Lines> linesDelivery() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.rxjava3.subjects.BehaviorSubject<com.shindra.ctslibrary.bo.EstimatedTimeTable> estimatedTimeTable(@org.jetbrains.annotations.NotNull()
    com.shindra.ctslibrary.bo.RouteType routeType, @org.jetbrains.annotations.NotNull()
    java.lang.String lineRef, int direction) {
        return null;
    }
    
    public MyViewModel() {
        super();
    }
}