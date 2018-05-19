var webpack = require('webpack');
const merge = require('webpack-merge');
const common = require('./webpack.common.js');
const UglifyJsPlugin = require('uglifyjs-webpack-plugin');

module.exports = merge(common, {
    plugins: [
        new UglifyJsPlugin({
          uglifyOptions:{
            output: {
              comments: false, // remove comments
            },
            compress: {
              unused: true,
              dead_code: true, // big one--strip code that will never execute
              warnings: false, // good for prod apps so users can't peek behind curtain
              drop_debugger: true,
              conditionals: true,
              evaluate: true,
              drop_console: true,
              sequences: true,
              booleans: true,
            },
          },
          sourceMap: true
        }),
        new webpack.DefinePlugin({
            'process.env.NODE_ENV': JSON.stringify('production')
        })
      ],
});