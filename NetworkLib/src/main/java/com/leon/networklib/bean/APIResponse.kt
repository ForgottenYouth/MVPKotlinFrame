package com.leon.networklib.bean

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * 将服务器返回的数据bean根据data的类型进行拆分
 */
abstract class APIResponse<T>(isShow: Boolean = false) //主构造
    : Observer<NetResponseBaseWrapperBean<T>> {

    private var isShowLoading: Boolean = isShow

    /**
     * 成功则返回data中的数据
     */
    abstract fun success(data: T)

    /**
     * 失败返回错误码和错误信息
     */
    abstract fun failure(errorMessage: String?, errorCode: Int)

    /**
     * TODO 显示加载对话框
     */
    abstract fun showLoadingDialog()

    /**
     * TODO 隐藏对话框
     */
    abstract fun dismissLoadingDialog()


    //启点分发的时候
    override fun onSubscribe(d: Disposable) {
        if (isShowLoading) {
            showLoadingDialog()
        }
    }

    override fun onNext(t: NetResponseBaseWrapperBean<T>) {
        if (t.data == null) {
            failure(t.errorMessage, t.errorCode)
        } else {
            success(t.data)
        }
    }

    override fun onError(e: Throwable) {
        failure(e.message, -1)
    }

    override fun onComplete() {
        dismissLoadingDialog()
    }
}