const path = require("path");
const HtmlWebPackPlugin = require("html-webpack-plugin");

const base = "/eproduct/";

module.exports = {
  output: {
    path: path.resolve(__dirname, "dist"),
    filename: "js/[name].js",
    publicPath: base
  },
  module: {
    rules: [
      {
        test:/\.html$/,
        use: {
          loader: "html-loader",
          options: {
            attrs: false,
            minimize: false,
            removeComments: false,
            collapseWhitespace: false,
          }
        }
      },
      {
        test: /\.js$/,
        exclude: /node_modules/,
        use: {
          loader: "babel-loader"
        }
      },
      {
          test: /^((?!\.global|react-select).)*\.css$/,
          loaders: [
              'style-loader',
              'css-loader?modules&importLoaders=1&localIdentName=[path]___[name]__[local]___[hash:base64:5]'
          ]
      },
      {
          test: /(\.global\.css$|react-select.css)/,
          loaders: [
              'style-loader',
              'css-loader'
          ]
      }
    ]
  },
  entry: {
    index: "./src/index.js",
    admin: "./src/admin.js"
  },
  devServer: {
    port: 9000
  },
  plugins: [
    new HtmlWebPackPlugin({
      template: "./public/index.html",
      filename: "./index.html",
      chunks: ["index"]
    }),
    new HtmlWebPackPlugin({
      template: "./public/admin.html",
      filename: "./admin.html",
      chunks: ["admin"]
    })
  ]
};