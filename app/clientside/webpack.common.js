var webpack = require('webpack');
var path = require('path');
const ExtractTextPlugin = require("extract-text-webpack-plugin");
const CleanWebpackPlugin = require('clean-webpack-plugin');

var config = {
  watch: true,
  entry: {
    'main': path.resolve(__dirname, 'src/main-entry.js')
  },
  output: {
    path: path.resolve(__dirname, '../../public/dist'),
    //filename: 'scripts/[name].js',
    filename: 'scripts/[name].[chunkhash:8].js',
    publicPath: 'dist'
  },
  resolve: {
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
    //new ExtractTextPlugin('styles/[name].css'),
    new ExtractTextPlugin('styles/[name].[contenthash:8].css'),
    new CleanWebpackPlugin(
      ['scripts/main.*.js','styles/main.*.css',],
      {
          root: path.resolve(__dirname, '../../public/dist'),
          verbose:  true,
          dry:      false
      }
  )
  ],
  externals: {
    "react": 'React',
    "react-dom": "ReactDOM",
  },
}

module.exports = config;