//跨域配置
module.exports = {
    devServer: {         //devServer 默认本地默认接口 选填
        port: 9876,
        proxy: {         //设置代理，必须填
            '/api': {    //设置拦截器， 格式：斜杠 + 拦截器名字，名字自己定
                target: 'http://localhost:9090',  //代理的目标地址
                changeOrigin: true,               //是否设置同源
                pathRewrite: {                    //路径重写
                    '/api': ''                   //选择忽略拦截器里面的单词
                }
            }
        }
    }
}