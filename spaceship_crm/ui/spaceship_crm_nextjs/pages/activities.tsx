/*
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
import React from 'react';
import RestService from "../services/RestService";
import {UserActivity} from "../types/Types";
import Grid from "@material-ui/core/Grid";
import TableContainer from "@material-ui/core/TableContainer";
import Table from "@material-ui/core/Table";
import Paper from "@material-ui/core/Paper";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import TableBody from "@material-ui/core/TableBody";
import {Link} from "@material-ui/core";
import serverCookies from 'next-cookies';
import {GetServerSideProps} from "next";

export default function (props) {
    const [ activities ] = React.useState<UserActivity[]>(props.activities);

    return (
        <Grid
            container
            spacing={0}
            direction="column"
            alignItems="center"
            justify="center"
            style={{ minHeight: '100vh' }}
        >
            <TableContainer component={Paper}>
                <Table size="small" aria-label="a dense table">
                    <TableHead>
                        <TableRow>
                            <TableCell>First Name</TableCell>
                            <TableCell>Last Name</TableCell>
                            <TableCell>Activity</TableCell>
                            <TableCell align="right">Start Date</TableCell>
                            <TableCell align="right">End DAte</TableCell>
                            <TableCell align="right">Added date</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {activities && activities.map((ua: UserActivity, index: number) => (
                            <TableRow key={index}>
                                <TableCell component="th" scope="row">
                                    {ua.firstName}
                                </TableCell>
                                <TableCell>{ua.lastName}</TableCell>
                                <TableCell>{ua.activityName}</TableCell>
                                <TableCell align="right">{ua.start}</TableCell>
                                <TableCell align="right">{ua.end}</TableCell>
                                <TableCell align="right">{ua.added}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
            <Link href={'/addusertoactivity'}>Add user to activity</Link>
        </Grid>
    );
}

export const getServerSideProps: GetServerSideProps = async context => {
    const { token } = serverCookies(context);
    const activities: UserActivity[] = await RestService.getActivities();
    return { props: { activities: activities } }
}