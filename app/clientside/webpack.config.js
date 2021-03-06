var webpack = require('webpack');
var path = require('path');
const ExtractTextPlugin = require("extract-text-webpack-plugin");
const UglifyJsPlugin = require('uglifyjs-webpack-plugin');

var config = {
  watch: true,
  entry: {
    'main': path.resolve(__dirname, 'src/main-entry.js')
  },
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
      },
      {
        test: /\.js|jsx$/, 
        exclude: /node_modules/,
        loader: 'eslint-loader'
      },
      // {
      //   test: /\.(jpg|png)$/,
      //   loader: 'url-loader?limit=100000'
      // },
      // {
      //   test: /\.jpe?g$|\.gif$|\.png$/i,
      //   loader: "file-loader?name=/img/[name].[ext]"

      // },
      {
        test: /\.(png|jp(e*)g|svg)$/,  
        use: [{
            loader: 'url-loader',
            options: { 
                limit: 8000, // Convert images < 8kb to base64 strings
                name: '/assets/images/[name].[ext]'
            } 
        }]
      }
    ]
  },

  plugins: [
    new ExtractTextPlugin('[name].css'),
    new webpack.DefinePlugin({
        'process.env': {
            'NODE_ENV': JSON.stringify('dev') 
        }
    }),
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
          drop_console: true, // strips console statements
          sequences: true,
          booleans: true,
        }
      },
    }),
  ],
  devtool: 'inline-source-map',

  externals: {
    "react": 'React',
    "react-dom": "ReactDOM",
  },
}

module.exports = config;