package com.albertobay.paymentapp.presenter;

import com.albertobay.paymentapp.R;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by Alberto Bay on 31,October,2018
 */
public class AmountPresenter extends Presenter<AmountPresenter.View> {

   /* private ArtistsInteractor interactor;

    public AmountPresenter(ArtistsInteractor interactor) {
        this.interactor = interactor;
    }*/

    public void onSearchAmount() {
        getView().showLoading();
                getView().hideLoading();
                getView().renderAmount();
        }

    public void launchArtistDetail(String amount) {
        getView().launchAmountDetail(amount);
    }

    @Override public void terminate() {
        super.terminate();
        setView(null);
    }

    public interface View extends Presenter.View {

        void showLoading();

        void hideLoading();

        void showArtistNotFoundMessage();

        void showConnectionErrorMessage();

        void showServerError();

        void renderAmount();

        void launchAmountDetail(String artist);
    }
}
