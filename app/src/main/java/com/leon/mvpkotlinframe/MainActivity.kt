package com.leon.mvpkotlinframe

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.leon.mvpkotlinframe.serviceapi.ServiceAPI
import com.leon.mvpkotlinframe.model.LoginResponseBean
import com.leon.networklib.NetWorkClient
import com.leon.networklib.bean.APIResponse
import com.leon.networklib.bean.NetResponseBaseWrapperBean
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        login.setOnClickListener(clickListener)
    }

    private val clickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.login -> {
                loginBtnClick()
            }
        }
    }


    var dialog: Dialog? = null;

    fun loginBtnClick() {

        dialog = Dialog(this)

        NetWorkClient.instance.instanceRetrofit(ServiceAPI::class.java)
            .login(
                "Derry-vip",
                "123456")
            .subscribeOn(Schedulers.io()) // 给上面请求服务器的操作，分配异步线程
            .observeOn(AndroidSchedulers.mainThread()) // 给下面更新UI操作，分配main线程
            .subscribe(object : APIResponse<LoginResponseBean>(true) {
                override fun success(data: LoginResponseBean) {
                    //请求成功回调
                    Toast.makeText(this@MainActivity, "登录成功😀", Toast.LENGTH_SHORT).show()
                }

                override fun failure(errorMessage: String?, errorCode: Int) {
                    //请求失败
                    Toast.makeText(this@MainActivity, "登录失败 ~ 呜呜呜", Toast.LENGTH_SHORT).show()
                }

                override fun showLoadingDialog() {
                    //此处新增加载中的对话框
                    dialog?.setContentView(R.layout.dialog_loading_layout)
                    dialog?.show()
                }

                override fun dismissLoadingDialog() {
                    //此处作关闭对话框
                    dialog?.dismiss()
                }

            })
    }
}