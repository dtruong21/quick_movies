package app.cmtruong.com.quickmovies.models

/**
 * List of review
 *
 * @author Davide Truong
 * @version 1.0
 * @since October 19th, 2018
 */
class ReviewList(var id: Int, var page: Int, var total_pages: Int, var total_results: Int, var results: List<Reviews>)