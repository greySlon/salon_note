import React from 'react'


export default class _Select extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      value: null
    }
    this.onSelect = this.onSelect.bind(this);
  }

  componentDidMount() {
    this.state.value = this.refs.self.value;
  }

  onSelect(e) {
    console.log(e.currentTarget.value);
    this.state.value = e.currentTarget.value;
  }

  render() {
    const mapObj = this.props.map;
    return (
        <select ref="self" onChange={this.onSelect}>
          {mapObj.map((item, i) =>
              <option key={i} value={item.value}>{item.key}</option>)}
        </select>
    )
  }
}