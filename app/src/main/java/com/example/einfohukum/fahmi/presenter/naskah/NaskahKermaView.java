package com.example.einfohukum.fahmi.presenter.naskah;

import com.example.einfohukum.fahmi.model.naskah.ResultItemNaskahKerma;
import java.util.List;

public interface NaskahKermaView {
    void onSuccess(String message, List<ResultItemNaskahKerma> result);
    void onError(String message);
    void onEmpty();
    void hideProgress();
    void hideLoading();
}
