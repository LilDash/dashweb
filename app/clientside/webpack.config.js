var webpack = require('webpack');
var path = require('path');
const ExtractTextPlugin = require("extract-text-webpack-plugin");

var config = {
  watch: true,
  entry: [
    path.resolve(__dirname, 'src/entry.js')
  ],
  output: {                                       // 定义出口目录
    path: path.resolve(__dirname, '../../public/dist'),
    filename: '[name].js',
    publicPath: 'dist'
  },
  resolve: {                                      // resolve 指定可以被 import 的文件后缀
    extensions: ['*', '.js', '.jsx']
  },
  module: {
    rules: [
      { 
        test: /\.js|jsx$/, 
        exclude: /node_modules/, 
        loader: 'babel-loader',
        query:{
          presets: ["es2015", "react"]
        }  
      },
      {
        test: /\.s?css$/,
        loader: ExtractTextPlugin.extract({
            fallback: 'style-loader', // backup loader when not building .css file
            use: ['css-loader', 'sass-loader'] // loaders to preprocess CSS
        })
      }
    ]
  },
  plugins: [
    new ExtractTextPlugin('[name].css'),
    new webpack.DefinePlugin({
        'process.env': {
            'NODE_ENV': JSON.stringify('dev') 
        }
    })
  ],
  devtool: 'inline-source-map',
}

module.exports = config;