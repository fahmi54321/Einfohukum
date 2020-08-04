package com.example.einfohukum.fahmi.presenter.psh;

import com.example.einfohukum.fahmi.model.psh.ResultItemPsh;

import java.util.List;

public interface PshView {
    void onSuccess(String message, List<ResultItemPsh> result);
    void onError(String message);
    void onEmpty();
    void hideProgress();
    void hideLoading();
}
