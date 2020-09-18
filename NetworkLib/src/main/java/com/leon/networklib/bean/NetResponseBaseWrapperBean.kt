package com.leon.networklib.bean

import java.io.Serializable

/**
 * 网络请求返回的包装bean
 */
data class NetResponseBaseWrapperBean<T>(
                                var data: T,
                                var errorMessage: String?,
                                var errorCode: Int,
                                var pageIndex:Int,
                                var totalRecord:Int
                                )
                            :Serializable