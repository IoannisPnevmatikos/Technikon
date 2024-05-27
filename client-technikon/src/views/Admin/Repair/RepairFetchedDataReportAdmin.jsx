import useToken from '../../../stores/useToken';
import React from 'react';
import { useLocation } from 'react-router-dom';

const RepairFetchedDataReportAdmin = () => {
    const { token } = useToken();

    const location = useLocation();
    const searchParams = new URLSearchParams(location.search);
    const dataString = searchParams.get('data');
    const data = dataString ? JSON.parse(decodeURIComponent(dataString)) : null;
    console.log(data);
    return (
        <div style={{ textAlign: 'center', fontSize: '20px', margin: '20px' }}>
            <h1 style={{ marginTop: '80px', marginBottom: '20px' }}>Repairs</h1>
            {data && (
                <ul style={{ listStyleType: 'none', padding: 0 }}>
                    {data.map((repair, index) => (
                        <li key={index} style={{ marginBottom: '20px', padding: '10px', border: '1px solid #ccc', borderRadius: '5px' }}>
                            {/* Render individual repair details here */}
                            <p><strong>Status of Repair:</strong> {repair.statusOfRepair}</p>
                            <p><strong>Total Cost:</strong> {repair.totalCost}</p>
                            <p><strong>Number Of Repairs:</strong> {repair.numberOfRepairs}</p>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

export default RepairFetchedDataReportAdmin;