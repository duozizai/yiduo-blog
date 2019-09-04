const dev_config = require('./config/dev.js');
const test_config = require('./config/test.js');
const prodctioin_config = require('./config/prod.js');

const env = process.env.NODE_ENV;//当前的环境
let config = null;

if(env=='prod'){
    config = prodctioin_config;
}else if(env=='test'){
    config = test_config;
}else{
    config = dev_config;
};
console.log(dev_config)
module.exports = config
