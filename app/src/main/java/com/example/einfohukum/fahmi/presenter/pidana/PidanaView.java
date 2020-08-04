package com.example.einfohukum.fahmi.presenter.pidana;

import com.example.einfohukum.fahmi.model.kke.ResultItemKke;
import com.example.einfohukum.fahmi.model.pidana.ResultItemPidana;

import java.util.List;

public interface PidanaView {
    void onSuccess(String message, List<ResultItemPidana> result);
    void onError(String message);
    void onEmty();
    void hideProgress();
    void hideLoading();
}
