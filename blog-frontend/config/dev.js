module.exports = {
    devServer: {
        host: 'localhost',
        port: 8080,
        https: false,// true 浏览器会给一些警告
        hotOnly: false,//热更新
        open: true, //项目启动是否直接在浏览器打开
        proxy: {
            '/api':{
                target:  'http://localhost:8081',//
                ws: true, //是否跨域
                changeOrigin: true,
                pathRewrite: {
                    '^/api': '/'
                }
            }
        }
    },
}