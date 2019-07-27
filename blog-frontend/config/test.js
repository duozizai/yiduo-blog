const merge = require('webpack-merge')
const dev_config = require('./dev.js');
module.exports = merge(dev_config,{
    runtimeCompiler: false
})
