import React from 'react';
import WorkItem from "./workItem";

export default class Day extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    const workitems = this.props.workitems;
    const masters = this.props.masters;
    console.log("day")
    return (
        <div className="schedule">
          <h2>{this.props.day} ({this.props.date})</h2>
          {
            workitems.map((workitem, i) =>
                <WorkItem key={i} workitem={workitem} masters={masters}/>
            )
          }
        </div>
    )
  }
}