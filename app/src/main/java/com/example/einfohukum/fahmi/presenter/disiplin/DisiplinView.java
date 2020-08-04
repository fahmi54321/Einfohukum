package com.example.einfohukum.fahmi.presenter.disiplin;

import com.example.einfohukum.fahmi.model.disiplin.ResultItemDisiplin;

import java.util.List;

public interface DisiplinView {
    void onSuccess(String message, List<ResultItemDisiplin> result);
    void onError(String message);
    void onEmty();
    void hideProgress();
    void hideLoading();
}
