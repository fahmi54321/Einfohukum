package com.example.einfohukum.fahmi.presenter.kke;

import com.example.einfohukum.fahmi.model.kke.ResultItemKke;


import java.util.List;

public interface KkeView {
    void onSuccess(String message, List<ResultItemKke> result);
    void onError(String message);
    void onEmty();
    void hideProgress();
    void hideLoading();
}
