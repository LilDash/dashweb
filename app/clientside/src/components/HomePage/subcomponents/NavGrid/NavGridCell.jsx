import * as React from 'react';
import PropTypes from 'prop-types';
import { CenterCroppedImage } from '../../../common/CenterCroppedImage/CenterCroppedImage';


class NavGridCell extends React.PureComponent {

	constructor(props) {
	    super(props);
	    //this.imageOnLoad = this.imageOnLoad.bind(this);
	    this.state = {
			isHover: false,
		};
		
	}


	handleMouseEnter(){
		this.setState({
			isHover: true,
		});
	}
	
	handleMouseLeave(){
		this.setState({
			isHover: false,
		})
	}

	handleOnClick(){
		this.props.onCellClick(this.props.categoryId, this.props.title);
	}

	render() {
		const isActiveClass = this.props.isActive ? 'active' : '' ;
		return (
			<div 
				className=
					{`nav-grid-cell nav-grid-cell-${ this.props.size } nav-grid-cell-${ this.props.name } ${isActiveClass}`}
				onMouseEnter={ this.handleMouseEnter.bind(this) }
				onMouseLeave={ this.handleMouseLeave.bind(this) }
				onClick={ this.handleOnClick.bind(this) }
			>
				<CenterCroppedImage 
					className='nav-grid-cell-img' 
					src={ this.props.imageSrc }
					isLazyload={ true }
					preloadColor={ this.props.imagePreloadColor }
					withShadow={ this.props.withShadow }
					shadowDarker={ this.state.isHover }
					displayLoading={ this.props.displayLoading }
				/>
				<div className='nav-grid-cell-text'>
					<span className='nav-grid-cell-title'>{ this.props.title }</span>
					<p className='nav-grid-cell-desc'>{ this.props.description }</p>
				</div>
			</div>
		);
	}
};

NavGridCell.propTypes = {
	name: PropTypes.string.isRequired,
  	imageSrc: PropTypes.string.isRequired,
  	title: PropTypes.string.isRequired,
  	description: PropTypes.string.isRequired,
  	categoryId: PropTypes.number.isRequired,
	imagePreloadColor: PropTypes.string,
	size: PropTypes.number.isRequired,
	withShadow: PropTypes.bool,
	displayLoading: PropTypes.bool,
	onCellClick: PropTypes.func.isRequired,
	isActive: PropTypes.bool,
};

NavGridCell.defaultProps = {
	withShadow: false,
	displayLoading: true,
	isActive: false,
};

export { NavGridCell };