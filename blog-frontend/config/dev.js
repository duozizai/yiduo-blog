module.exports = {
    runtimeCompiler: true,//是否使用包含运行时编译器的 Vue 构建版本。设置为 true 后你就可以在 Vue 组件中使用 template 选项了，但是这会让你的应用额外增加 10kb 左右。(默认false)
    devServer: {
        host: 'localhost',
        port: 8080,
        https: false,// true 浏览器会给一些警告
        hotOnly: false,//热更新
        open: true, //项目启动是否直接在浏览器打开
        proxy: {
            '/api':{
                target:  `${process.env.VUE_APP_API_URL}`,//
                ws: true, //是否跨域
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    },
}
