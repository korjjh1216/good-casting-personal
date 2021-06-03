import React, { useCallback, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fileSelector, fileRegister } from '../../state/reducer/file.reducer';
import { profileList } from '../../state/reducer/profile.reducer';

const DragNDropComponent = ({ pageRequest }) => {
    const dispatch = useDispatch();
    const file = useSelector(fileSelector).fileList;

    const uploadAjax = useCallback((e) => {
        console.dir(e.target.files);

        const formData = new FormData();
        const files = e.target.files;

        for (let i = 0; i < files.length; i++) {
            formData.append('uploadFiles', files[i]);
        }

        dispatch(fileRegister(formData));
    });

    useEffect(() => {
        if (file.fileName) {
            dispatch(
                profileList({
                    ...pageRequest,
                    file: {
                        fileName: file.fileName,
                        uuid: file.uuid,
                    },
                })
            );
        }
    }, [file]);

    return (
        <>
            <div className="upload-file mb-16 text-center">
                <div id="userActions" className="square-144 m-auto px-6 mb-7">
                    {file.fileName ? (
                        <img style={{ width: '150px', height: '200px' }} src={'http://localhost:8080/files/display?fileName=s_' + file.uuid + '_' + file.fileName} />
                    ) : (
                        <>
                            <label htmlFor="fileUpload" className="mb-0 font-size-4 text-smoke">
                                Browse or Drag and Drop
                            </label>
                            <input type="file" id="fileUpload" className="sr-only" onChange={uploadAjax} />
                        </>
                    )}
                </div>
            </div>
        </>
    );
};

export default DragNDropComponent;
